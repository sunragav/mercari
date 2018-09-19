package com.mercari.sundararaghavan.myapplication.networking;

import com.mercari.sundararaghavan.myapplication.products.model.MasterRepo;
import com.mercari.sundararaghavan.myapplication.products.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RepoService {

    @GET
    Call<List<Repo>> getRepositories(@Url String url);

    @GET
    Call<List<MasterRepo>> getMasterRepositories(@Url String url);
}
