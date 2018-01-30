package com.skripsi.mrizk.findingdosen.repository.datasource.api;

import com.skripsi.mrizk.findingdosen.repository.entity.api.FetchDosenResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by mrizk on 30/01/2018.
 */

public interface IFetchDosenRequest {

    @GET("user/fetch_dosen")
    Observable<Response<FetchDosenResponse>> fetchDosen(@Header("Authorization") String authorization);
}
