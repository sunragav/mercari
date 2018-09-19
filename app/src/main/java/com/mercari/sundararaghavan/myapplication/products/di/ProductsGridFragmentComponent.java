package com.mercari.sundararaghavan.myapplication.products.di;

import com.mercari.sundararaghavan.myapplication.home.MainActivity;
import com.mercari.sundararaghavan.myapplication.products.view.ProductsGridFragment;

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