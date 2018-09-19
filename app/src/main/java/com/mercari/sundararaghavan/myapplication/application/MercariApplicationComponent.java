package com.mercari.sundararaghavan.myapplication.application;

import android.app.Application;
import android.content.Context;

import com.mercari.sundararaghavan.myapplication.home.ActivityBuilder;
import com.mercari.sundararaghavan.myapplication.home.MainActivity;
import com.mercari.sundararaghavan.myapplication.networking.NetworkModule;
import com.mercari.sundararaghavan.myapplication.products.view.FragmentBuilder;
import com.mercari.sundararaghavan.myapplication.products.view.ProductsGridFragment;
import com.mercari.sundararaghavan.myapplication.viewmodel.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

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
