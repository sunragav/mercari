package com.mercari.sundararaghavan.myapplication.viewmodel.di;

import android.arch.lifecycle.ViewModel;

import com.mercari.sundararaghavan.myapplication.products.viewmodel.ProductsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProductsViewModel.class)
    abstract ViewModel bindProductsViewModel(ProductsViewModel viewModel);
}
