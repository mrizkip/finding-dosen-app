package com.skripsi.mrizk.findingdosen.repository.entity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by mrizk on 30/01/2018.
 */

public class FetchDosenResponse {

    @SerializedName("data")
    @Expose
    private List<DosenListResponse> data;

    public List<DosenListResponse> getData() {
        return data;
    }

    public void setData(List<DosenListResponse> data) {
        this.data = data;
    }
}
