package com.skripsi.mrizk.findingdosen.repository.entity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mrizk on 16/01/2018.
 */

public class LoginResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("logged_in_user")
    @Expose
    private UserRemote loggedInUser;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserRemote getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(UserRemote loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
