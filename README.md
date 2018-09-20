# MercariApplication
### Choice A: A sample app for training
An Application that looks like the Mercari App, loads products based on the static json files from AWS S3 URL , in a tab layout using viewpager.
The application is built using Dagger2(for dependency injections), Android architecture components( Viewmodel, LiveData, MutableLiveData), Moshi(JSON adapter), AutoValue ( for generating the equals, hashcode and other useful boilerplate generations for the models), Retrofit( for network calls), ButterKnife(for view bindings).
The app follows the (Model View ViewModel) MVVM architecture where the ViewModel is contructed by extending the ViewModel of android architecture comonents.

The apps dependencies are satisfied using the Dagger2.0 library.

## The following is the dependency diagram:
![Dagger2 dependency diagram](./doc-img/Dagger2.JPG)
The packaging structure has been maintained in such a way that all the logical pieces of the application has a package name and their dependency injection related files are placed in the "di" folder as shown in the image below.
![Dagger2 dependency package structure](./doc-img/di.JPG)
### NetWorkModule provides the retrofit(for the service calls) and the moshi adapter(for the JSON seraialization and deserializations)
```java
@Module
public abstract class NetworkModule {

    private static final String BASE_URL = "https://s3-ap-northeast-1.amazonaws.com";
    @Provides
    @Singleton
    static Moshi provideMoshi() {
        return new Moshi.Builder()
                .add(AdapterFactory.create())
                .build();
    }
    @Provides
    @Singleton
    static Retrofit provideRetrofit(Moshi moshi) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();
    }

    @Provides
    @Singleton
    static RepoService provideRepoService(Retrofit retrofit) {
        return retrofit.create(RepoService.class);
    }
}
```
### AcitivityBuilder Module uses the Dagger2 multibinding to bind the factory of the MainActivity for the key of MainActivity.class.
```java
@Module
public abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivity(MainActivityComponent.Builder builder);
}
```
### MainActivityComponent injects the MainActivity using the factory provided by the ActivityBuilder modlue.
```java

@Subcomponent
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {

        @Override
        public void seedInstance(MainActivity instance) {

        }
    }
}
```
### AppModule includes all the other subcommponents for the application.
```java
@Module(subcomponents = {
        MainActivityComponent.class,
        ProductsGridFragmentComponent.class,
})
public class AppModule {

    private final Application application;

    AppModule(Application application) {
        this.application = application;
    }

}
```
### AppComponent takes all the dependencies provided by the modules and injects them in the places requested.
```java
@Singleton
@Component(modules = {
        ActivityBuilder.class,
        FragmentBuilder.class,
        AppModule.class,
        NetworkModule.class,
        ViewModelModule.class,
})
public interface MercariApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        MercariApplicationComponent build();
    }

    void inject(MercariApplication application);

    void inject(ProductsGridFragment productGridFragment);

    void inject(MainActivity mainActivity);
}
```
### ViewModelFactory creates the ViewModel that is used by both the MainActivity and the ProductsGridFragment
```java
@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModels;

    @Inject
    ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModels) {
        this.viewModels = viewModels;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            //noinspection unchecked
            return (T) viewModels.get(modelClass).get();
        } catch (Exception e) {
            throw new RuntimeException("Error creating view model for class: " + modelClass.getSimpleName(), e);
        }
    }
}
```java
//MainActivity.java
  protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(actionBar);
        getSupportActionBar().setIcon(R.mipmap.ic2_launcher);

        customPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager());
        viewModel = ViewModelProviders.of(this, this.viewModelFactory).get(ProductsViewModel.class);
        
```

```
### ViewModelModule provides the viewmodel object via the Dagger2 MultiBinding 
```java
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProductsViewModel.class)
    abstract ViewModel bindProductsViewModel(ProductsViewModel viewModel);
}
```
### ProductsGridFragmentComponent that injects the dependecies for the ProductFragmentComponent.
```java
@Subcomponent
public interface ProductsGridFragmentComponent extends AndroidInjector<ProductsGridFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ProductsGridFragment> {

        @Override
        public void seedInstance(ProductsGridFragment instance) {

        }
    }
}
```


## Architecture
The android architecture component's viewmodel survive's configuration changes like device orientation etc.
The LiveData has been used to intimate the various states the service call go through like loading starts, response arrives or error occurs. On both, success as well failure the loading is propmptly stopped and error is intimated via the text field in a graceful way without exiting the app.
This is handled in the following code snippet:
 ```java
 private void fetchRepos(String category, String url) {
        Timber.d("ProductsViewModel", "fetchRepos API called for category:" + category);
        initLiveDataMap(category);
        LiveModel liveModel = liveDataMap.get(category);
        liveModel.loading.setValue(true);
        repoCall = repoService.getRepositories(url);
        repoCall.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                liveModel.repoLoadError.setValue(false);
                liveModel.repos.setValue(response.body());
                Timber.d("ProductsViewModel", "API call succeded for category:" + category);
                liveModel.loading.setValue(false);
                repoCall = null;
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.e(getClass().getSimpleName(), "Error loading repos", t);
                liveModel.repoLoadError.setValue(true);
                liveModel.loading.setValue(false);
                // liveDataMap.put(category,liveModel);
                repoCall = null;
            }
        });
    }
```

![App architecture diagram](./doc-img/architecture.JPG)

## Screenshots
<img src="./doc-img/device.png" width="400">

## CI/CD using nevercode.io and HockeyApp (No configuration script required).
![nevercode.io 1](./doc-img/nevercode1.JPG)
![nevercode.io 2](./doc-img/nevercode2.JPG)
![nevercode.io 3](./doc-img/nevercode3.JPG)
![nevercode.io 4](./doc-img/nevercode4.JPG)
![nevercode.io 5](./doc-img/nevercode5.JPG)
![nevercode.io 6](./doc-img/nevercode-pub.JPG)
## HockeyApp Distribution (automatically distributed from nevercode.io)
![hockeyapp distribution](./doc-img/hockey.JPG)
