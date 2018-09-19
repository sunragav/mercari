package com.mercari.sundararaghavan.myapplication.products.di;

import android.support.v4.app.Fragment;

import com.mercari.sundararaghavan.myapplication.products.view.ProductsGridFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module
public abstract class FragmentBuilder {

    @Binds
    @IntoMap
    @SupportFragmentKey(ProductsGridFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindMainActivity(ProductsGridFragmentComponent.Builder builder);
}
