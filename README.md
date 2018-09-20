# MercariApplication
An Application that looks like the Mercari App, loads products based on the static json files from AWS S3 URL , in a tab layout using viewpager
The application is built using Dagger2(for dependency injections), Android architecture components( Viewmodel, LiveData, MutableLiveData), Moshi(JSON adapter), AutoValue ( for generating the equals, hashcode and other useful boilerplate generations for the models), Retrofit( for network calls), ButterKnife(for view bindings).
The app follows the (Model View ViewModel) MVVM architecture where the ViewModel is contructed by extending the ViewModel of android architecture comonents.

The apps dependencies are satisfied using the Dagger2.0 library.

![alt text](image.jpg)
