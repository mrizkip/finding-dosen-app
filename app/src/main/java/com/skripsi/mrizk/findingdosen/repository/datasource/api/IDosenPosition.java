package com.skripsi.mrizk.findingdosen.repository.datasource.api;

import com.skripsi.mrizk.findingdosen.repository.entity.api.PosisiDosenResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface IDosenPosition {

    @GET("user/{id}/dosen_position")
    Single<PosisiDosenResponse> getDosenPosition(@Header("Authorization") String authorization, @Path("id") int userid);
}
