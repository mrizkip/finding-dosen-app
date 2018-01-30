package com.skripsi.mrizk.findingdosen.repository.entity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.Observable;

/**
 * Created by mrizk on 30/01/2018.
 */

public class FetchDosenResponse {

    @SerializedName("data")
    @Expose
    private Observable<UserRemote> data;

    public Observable<UserRemote> getData() {
        return data;
    }

    public void setData(Observable<UserRemote> data) {
        this.data = data;
    }
}
