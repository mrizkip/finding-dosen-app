package com.skripsi.mrizk.findingdosen.repository.datasource.api;

import com.skripsi.mrizk.findingdosen.repository.entity.api.LoginRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.api.LoginResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by mrizk on 18/01/2018.
 */

public interface ILoginRequest {

    @POST("login")
    Single<LoginResponse> login(@Body LoginRequest loginRequest);
}
