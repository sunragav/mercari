package com.mercari.sundararaghavan.myapplication.application;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.mercari.sundararaghavan.myapplication.BuildConfig;
import com.mercari.sundararaghavan.myapplication.application.di.DaggerMercariApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;
import timber.log.Timber;

public class MercariApplication extends Application implements HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        DaggerMercariApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this);
        if (BuildConfig.DEBUG_MODE) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }
}
