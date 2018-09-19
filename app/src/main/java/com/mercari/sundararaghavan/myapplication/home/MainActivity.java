package com.mercari.sundararaghavan.myapplication.home;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mercari.sundararaghavan.myapplication.R;
import com.mercari.sundararaghavan.myapplication.products.model.MasterRepo;
import com.mercari.sundararaghavan.myapplication.products.view.ProductsGridFragment;
import com.mercari.sundararaghavan.myapplication.products.viewmodel.ProductsViewModel;
import com.mercari.sundararaghavan.myapplication.viewmodel.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.FragmentKey;

public class MainActivity extends AppCompatActivity {
    private static final String MASTER_URL = "https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/master.json";
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tablelayout)
    TabLayout tabLayout;
    @BindView(R.id.recycler_view)
    RecyclerView listView;
    @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.loading_view)
    View loadingView;
    @BindView(R.id.frame_layout)
    FrameLayout mainFrameLayout;

    @Inject
    ViewModelFactory viewModelFactory;



    private CustomPagerAdapter customPagerAdapter;
    private ProductsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Resources res = getResources();
        String[] titles = res.getStringArray(R.array.categories);
        customPagerAdapter = new CustomPagerAdapter(titles, getSupportFragmentManager());
        ButterKnife.bind(this);


        viewModel = ViewModelProviders.of(this, this.viewModelFactory).get(ProductsViewModel.class);
        observeViewModel();
        viewModel.fetchMasterRepos(MASTER_URL);

        listView.setVisibility(View.GONE);
        tabLayout.setVisibility(View.GONE);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void observeViewModel() {
        viewModel.getMasterRepos().observe(this, repos -> {
            if (repos != null) {
                mainFrameLayout.setVisibility(View.GONE);
                tabLayout.setVisibility(View.VISIBLE);
                for (MasterRepo repo : repos) {
                    customPagerAdapter.getFragmentMap().get(repo.name()).setUrl(repo.data());
                }
                viewPager.setAdapter(customPagerAdapter);
                //String key ="Men";
                //viewModel.fetchChildRepos(key,customPagerAdapter.getFragmentMap().get(key).getUrl());
                //customPagerAdapter.notifyDataSetChanged();
            }
        });
        viewModel.getMasterRepoError().observe(this, isError -> {
            //noinspection ConstantConditions
            if (isError) {
                errorTextView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                errorTextView.setText(R.string.api_error_repos);
            } else {
                errorTextView.setVisibility(View.GONE);
                errorTextView.setText(null);
            }
        });
        viewModel.getMasterRepoLoading().observe(this, isLoading -> {
            //noinspection ConstantConditions
            loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            if (isLoading) {
                errorTextView.setVisibility(View.GONE);
                listView.setVisibility(View.GONE);
            }
        });
    }
}
