package com.mercari.sundararaghavan.myapplication.home;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mercari.sundararaghavan.myapplication.application.MercariApplication;
import com.mercari.sundararaghavan.myapplication.products.view.ProductsGridFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CustomPagerAdapter extends FragmentPagerAdapter {
    List<ProductsGridFragment> fragments = new ArrayList<>();


    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getCategory();
    }

    public void clear() {
        fragments.clear();
    }

    public void add(ProductsGridFragment fragment) {
        fragments.add(fragment);
    }

}
