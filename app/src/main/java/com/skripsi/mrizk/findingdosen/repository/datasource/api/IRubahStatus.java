package com.skripsi.mrizk.findingdosen.repository.datasource.api;

import com.skripsi.mrizk.findingdosen.repository.entity.api.RubahLokasiRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.api.RubahLokasiResponse;
import com.skripsi.mrizk.findingdosen.repository.entity.api.RubahStatusRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.api.RubahStatusResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRubahStatus {

    @POST("dosen/update_status")
    Single<RubahStatusResponse> ubahStatus(@Body RubahStatusRequest rubahStatusRequest);

    @POST("dosen/update_location")
    Single<RubahLokasiResponse> ubahLokasi(@Body RubahLokasiRequest rubahLokasiRequest);
}
