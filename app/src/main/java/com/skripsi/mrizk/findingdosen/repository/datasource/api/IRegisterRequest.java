package com.skripsi.mrizk.findingdosen.repository.datasource.api;

import com.skripsi.mrizk.findingdosen.repository.entity.api.RegisterRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.api.RegisterResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by mrizk on 15/03/2018.
 */

public interface IRegisterRequest {

    @POST("register")
    Single<RegisterResponse> register(@Body RegisterRequest registerRequest);
}
