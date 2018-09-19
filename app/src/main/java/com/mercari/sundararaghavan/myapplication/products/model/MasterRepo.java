package com.mercari.sundararaghavan.myapplication.products.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class MasterRepo {
    /**
     * {
     * "name": "All",
     * "data": "https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/all.json"
     * }
     */
    public abstract String name();

    public abstract String data();

    public static JsonAdapter<MasterRepo> jsonAdapter(Moshi moshi) {
        return new AutoValue_MasterRepo.MoshiJsonAdapter(moshi);
    }

}
