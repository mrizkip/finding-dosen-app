package com.skripsi.mrizk.findingdosen.repository;

import android.util.Log;

import com.skripsi.mrizk.findingdosen.repository.datasource.api.ILoginRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.User;
import com.skripsi.mrizk.findingdosen.repository.entity.api.LoginRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.api.LoginResponse;
import com.skripsi.mrizk.findingdosen.repository.transformer.LoginResponseToUser;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mrizk on 08/03/2018.
 */

public class UserRepository {
    private static final String TAG = "UserRepository";

    private final ILoginRequest iLoginRequest;
    private final LoginResponseToUser loginResponseToUser;

    @Inject
    public UserRepository(ILoginRequest iLoginRequest, LoginResponseToUser loginResponseToUser) {
        this.iLoginRequest = iLoginRequest;
        this.loginResponseToUser = loginResponseToUser;
    }

    public Observable<User> loginUser(LoginRequest loginRequest) {
        return iLoginRequest.login(loginRequest)
                .toObservable()
                .map(loginResponseToUser::transform)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
