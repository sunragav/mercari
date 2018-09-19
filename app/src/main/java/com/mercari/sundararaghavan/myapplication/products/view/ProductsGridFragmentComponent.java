package com.mercari.sundararaghavan.myapplication.products.view;

import com.mercari.sundararaghavan.myapplication.home.MainActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent
public interface ProductsGridFragmentComponent extends AndroidInjector<ProductsGridFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ProductsGridFragment> {

        @Override
        public void seedInstance(ProductsGridFragment instance) {

        }
    }
}