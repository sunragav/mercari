package com.mercari.sundararaghavan.myapplication.products.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.mercari.sundararaghavan.myapplication.R;
import com.mercari.sundararaghavan.myapplication.application.MercariApplication;
import com.mercari.sundararaghavan.myapplication.networking.RepoService;
import com.mercari.sundararaghavan.myapplication.products.model.MasterRepo;
import com.mercari.sundararaghavan.myapplication.products.model.Repo;
import com.mercari.sundararaghavan.myapplication.products.view.ProductsGridFragment;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


public class ProductsViewModel extends ViewModel {

    private final RepoService repoService;

    private MasterRepo masterRepo;
    private final MutableLiveData<List<MasterRepo>> repos = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    class LiveModel {
        private final MutableLiveData<List<Repo>> repos = new MutableLiveData<>();
        private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
        private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    }

    private Call<List<Repo>> repoCall;
    private Call<List<MasterRepo>> masterRepoCall;

    private HashMap<String, LiveModel> liveDataMap = new HashMap<>();


    @Inject
    public ProductsViewModel(RepoService repoService) {
        this.repoService = repoService;
    }


    public void fetchMasterRepos(String url) {
        loading.setValue(true);
        masterRepoCall = repoService.getMasterRepositories(url);
        masterRepoCall.enqueue(new Callback<List<MasterRepo>>() {
            @Override
            public void onResponse(Call<List<MasterRepo>> call, Response<List<MasterRepo>> response) {
                repoLoadError.setValue(false);
                repos.setValue(response.body());
                loading.setValue(false);
                masterRepoCall = null;
            }

            @Override
            public void onFailure(Call<List<MasterRepo>> call, Throwable t) {
                Log.e(getClass().getSimpleName(), "Error loading repos", t);
                repoLoadError.setValue(true);
                loading.setValue(false);
                masterRepoCall = null;
            }
        });
    }

    public void fetchChildRepos(String category, String url) {
        if ((!liveDataMap.containsKey(category) ||
                (!liveDataMap.get(category).loading.getValue() && liveDataMap.get(category).repos == null))) {

            fetchRepos(category, url);
        }

        //To modify this logic to fetchChildRepos from room db instead of in-memory map

    }

    public LiveData<List<Repo>> getRepos(String category) {
        return liveDataMap.get(category).repos;
    }

    public LiveData<Boolean> getError(String category) {
        return liveDataMap.get(category).repoLoadError;
    }

    public LiveData<Boolean> getLoading(String category) {
        return liveDataMap.get(category).loading;
    }

    public LiveData<List<MasterRepo>> getMasterRepos() {
        return repos;
    }

    public LiveData<Boolean> getMasterRepoError() {
        return repoLoadError;
    }

    public LiveData<Boolean> getMasterRepoLoading() {
        return loading;
    }

    private void fetchRepos(String category, String url) {
        LiveModel liveModel = new LiveModel();
        liveDataMap.put(category,liveModel);
        liveModel.loading.setValue(true);
        repoCall = repoService.getRepositories(url);
        repoCall.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                liveModel.repoLoadError.setValue(false);
                liveModel.repos.setValue(response.body());
                Timber.d("ProductsViewModel","API call succeded for category:"+category);
                liveModel.loading.setValue(false);
                liveDataMap.put(category, liveModel);
                repoCall = null;
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.e(getClass().getSimpleName(), "Error loading repos", t);
                liveModel.repoLoadError.setValue(true);
                liveModel.loading.setValue(false);
                // liveDataMap.put(category,liveModel);
                repoCall = null;
            }
        });
    }

    @Override
    protected void onCleared() {
        if (repoCall != null) {
            repoCall.cancel();
            repoCall = null;
        }
        if (masterRepoCall != null) {
            masterRepoCall.cancel();
            masterRepoCall = null;
        }
    }
}
