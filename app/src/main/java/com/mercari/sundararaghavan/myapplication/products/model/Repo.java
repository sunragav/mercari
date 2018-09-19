package com.mercari.sundararaghavan.myapplication.products.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@AutoValue
public abstract class Repo {
    /**
     * {
     * "id": "mmen1",
     * "name": "men1",
     * "status": "on_sale",
     * "num_likes": 91,
     * "num_comments": 59,
     * "price": 51,
     * "photo": "http://dummyimage.com/400x400/000/fff?text=men1"
     * }
     */
    public abstract String id();

    public abstract Long num_likes();

    public abstract Long num_comments();

    public abstract String status();

    public abstract Long price();

    public abstract String name();

    public abstract String photo();

    public static JsonAdapter<Repo> jsonAdapter(Moshi moshi) {
        return new AutoValue_Repo.MoshiJsonAdapter(moshi);
    }

}
