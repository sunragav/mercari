package com.mercari.sundararaghavan.myapplication.application.di;

import android.app.Application;

import com.mercari.sundararaghavan.myapplication.home.di.MainActivityComponent;
import com.mercari.sundararaghavan.myapplication.products.di.ProductsGridFragmentComponent;

import dagger.Module;

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