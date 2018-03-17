package com.skripsi.mrizk.findingdosen.repository.datasource.local;

import com.skripsi.mrizk.findingdosen.repository.datasource.api.ILoginRequest;
import com.skripsi.mrizk.findingdosen.repository.datasource.api.IRegisterRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.Register;
import com.skripsi.mrizk.findingdosen.repository.entity.User;
import com.skripsi.mrizk.findingdosen.repository.entity.api.LoginRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.api.RegisterRequest;
import com.skripsi.mrizk.findingdosen.repository.transformer.LoginResponseToUser;
import com.skripsi.mrizk.findingdosen.repository.transformer.RegisterResponseToRegister;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mrizk on 08/03/2018.
 */

public class UserRepository {
    private static final String TAG = "UserRepository";

    private final ILoginRequest iLoginRequest;
    private final IRegisterRequest iRegisterRequest;
    private final LoginResponseToUser loginResponseToUser;
    private final RegisterResponseToRegister registerResponseToRegister;

    @Inject
    public UserRepository(ILoginRequest iLoginRequest, IRegisterRequest iRegisterRequest, LoginResponseToUser loginResponseToUser, RegisterResponseToRegister registerResponseToRegister) {
        this.iLoginRequest = iLoginRequest;
        this.iRegisterRequest = iRegisterRequest;
        this.loginResponseToUser = loginResponseToUser;
        this.registerResponseToRegister = registerResponseToRegister;
    }

    public Observable<User> loginUser(LoginRequest loginRequest) {
        return iLoginRequest.login(loginRequest)
                .toObservable()
                .map(loginResponseToUser::transform)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Register> registerUser(RegisterRequest registerRequest) {
        return iRegisterRequest.register(registerRequest)
                .toObservable()
                .map(registerResponseToRegister::transform)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
