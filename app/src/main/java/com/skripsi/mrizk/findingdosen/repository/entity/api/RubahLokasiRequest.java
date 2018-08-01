package com.skripsi.mrizk.findingdosen.repository.entity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RubahLokasiRequest {

    @Expose
    @SerializedName("data")
    private List<AccessPointRequest> data;

    public List<AccessPointRequest> getData() {
        return data;
    }

    public void setData(List<AccessPointRequest> data) {
        this.data = data;
    }
}
