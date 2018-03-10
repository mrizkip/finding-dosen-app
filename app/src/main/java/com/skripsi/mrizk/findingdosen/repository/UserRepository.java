package com.skripsi.mrizk.findingdosen.repository;

import com.skripsi.mrizk.findingdosen.repository.datasource.api.ILoginRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.User;
import com.skripsi.mrizk.findingdosen.repository.entity.api.LoginRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.api.LoginResponse;
import com.skripsi.mrizk.findingdosen.repository.transformer.LoginResponseToUser;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by mrizk on 08/03/2018.
 */

public class LoginRepository {

    private final ILoginRequest iLoginRequest;
    private final LoginResponseToUser loginResponseToUser;
    private final LoginRequest loginRequest;

    @Inject
    public LoginRepository(ILoginRequest iLoginRequest, LoginResponseToUser loginResponseToUser, LoginRequest loginRequest) {
        this.iLoginRequest = iLoginRequest;
        this.loginResponseToUser = loginResponseToUser;
        this.loginRequest = loginRequest;
    }

    public Observable<User> login() {
        return iLoginRequest.login(loginRequest)
                .toObservable()
                .map(loginResponseToUser::transform)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
