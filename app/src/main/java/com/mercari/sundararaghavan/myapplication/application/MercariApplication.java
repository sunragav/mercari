package com.mercari.sundararaghavan.myapplication.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.mercari.sundararaghavan.myapplication.application.di.DaggerMercariApplicationComponent;
import com.mercari.sundararaghavan.myapplication.application.di.MercariApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;
import timber.log.BuildConfig;
import timber.log.Timber;

public class MercariApplication extends Application implements HasActivityInjector, HasSupportFragmentInjector {
    private MercariApplicationComponent component;
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
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static MercariApplicationComponent getApplicationComponent(Context context) {
        return ((MercariApplication) context.getApplicationContext()).component;
    }

    public MercariApplicationComponent getComponent() {
        return component;
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
