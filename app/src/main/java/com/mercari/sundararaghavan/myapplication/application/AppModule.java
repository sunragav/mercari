package com.mercari.sundararaghavan.myapplication.application;

import android.app.Application;
import android.content.Context;

import com.mercari.sundararaghavan.myapplication.home.MainActivityComponent;
import com.mercari.sundararaghavan.myapplication.products.view.ProductsGridFragmentComponent;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

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