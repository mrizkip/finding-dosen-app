package com.skripsi.mrizk.findingdosen.repository.datasource.api;

import com.skripsi.mrizk.findingdosen.repository.entity.api.LoginRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.api.LoginResponse;

import org.json.JSONObject;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by mrizk on 18/01/2018.
 */

public interface ILoginRequest {

    @POST("login")
    Observable<Response<LoginResponse>> login(@Body LoginRequest loginRequest);
}
