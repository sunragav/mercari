package com.mercari.sundararaghavan.myapplication.products.view;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.mercari.sundararaghavan.myapplication.home.MainActivity;
import com.mercari.sundararaghavan.myapplication.home.MainActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.FragmentKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class FragmentBuilder {

    @Binds
    @IntoMap
    @SupportFragmentKey(ProductsGridFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindMainActivity(ProductsGridFragmentComponent.Builder builder);
}
