package com.skripsi.mrizk.findingdosen.repository.entity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mrizk on 30/01/2018.
 */

public class ProfileDosenResponse {

    @SerializedName("data")
    @Expose
    private UserRemote data;

    public UserRemote getData() {
        return data;
    }

    public void setData(UserRemote data) {
        this.data = data;
    }
}
