package com.skripsi.mrizk.findingdosen.repository.entity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RubahLokasiResponse {

    @Expose
    @SerializedName("mse")
    private double mse;
    @Expose
    @SerializedName("posisi")
    private String posisi;

    public double getMse() {
        return mse;
    }

    public void setMse(double mse) {
        this.mse = mse;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }
}
