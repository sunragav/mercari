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

    public Map<String, ProductsGridFragment> getFragmentMap() {
        return fragmentMap;
    }

    Map<String,ProductsGridFragment> fragmentMap = new HashMap<>();
    public CustomPagerAdapter(String categories[], FragmentManager fm) {
        super(fm);
        for(int i=0;i<3;i++){
            fragments.add(new ProductsGridFragment());
            fragments.get(i).setCategory(categories[i]);
            fragmentMap.put(categories[i],fragments.get(i));
        }
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

    public void clear(){
        fragments.clear();
        notifyDataSetChanged();
    }

}
