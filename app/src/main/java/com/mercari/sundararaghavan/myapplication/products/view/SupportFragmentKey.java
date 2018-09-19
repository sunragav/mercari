package com.mercari.sundararaghavan.myapplication.products.view;

import android.support.v4.app.Fragment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import dagger.MapKey;

@MapKey
@Target(ElementType.METHOD)
public @interface SupportFragmentKey {

    Class<? extends Fragment> value();
}