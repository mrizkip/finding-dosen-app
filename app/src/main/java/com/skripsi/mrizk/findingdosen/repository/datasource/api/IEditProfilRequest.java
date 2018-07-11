package com.skripsi.mrizk.findingdosen.repository.datasource.api;

import com.skripsi.mrizk.findingdosen.repository.entity.api.EditProfilRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.api.EditProfilResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IEditProfilRequest {

    @POST("user/change_profile")
    Single<EditProfilResponse> editProfil(@Header("Authorization") String authorization, @Body EditProfilRequest editProfilRequest);
}
