package com.skripsi.mrizk.findingdosen.repository.datasource.api;

import com.skripsi.mrizk.findingdosen.repository.entity.api.MyProfileResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by mrizk on 30/01/2018.
 */

public interface IDosenProfileRequest {

    @GET("user/{userid}/profile")
    Observable<Response<MyProfileResponse>> myProfile(@Header("Authorization") String authorization, @Path("userid") String userid);
}
