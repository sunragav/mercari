package com.mercari.sundararaghavan.myapplication.application.di;

import android.app.Application;

import com.mercari.sundararaghavan.myapplication.application.MercariApplication;
import com.mercari.sundararaghavan.myapplication.home.MainActivity;
import com.mercari.sundararaghavan.myapplication.home.di.ActivityBuilder;
import com.mercari.sundararaghavan.myapplication.networking.di.NetworkModule;
import com.mercari.sundararaghavan.myapplication.products.di.FragmentBuilder;
import com.mercari.sundararaghavan.myapplication.products.view.ProductsGridFragment;
import com.mercari.sundararaghavan.myapplication.viewmodel.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

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
