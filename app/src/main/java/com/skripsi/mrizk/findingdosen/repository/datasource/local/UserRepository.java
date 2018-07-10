package com.skripsi.mrizk.findingdosen.repository.datasource.local;

import android.util.Log;

import com.skripsi.mrizk.findingdosen.repository.datasource.api.ILoginRequest;
import com.skripsi.mrizk.findingdosen.repository.datasource.api.IMyProfileRequest;
import com.skripsi.mrizk.findingdosen.repository.datasource.api.IRegisterRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.local.Register;
import com.skripsi.mrizk.findingdosen.repository.entity.local.User;
import com.skripsi.mrizk.findingdosen.repository.entity.api.LoginRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.api.RegisterRequest;
import com.skripsi.mrizk.findingdosen.repository.transformer.LoginResponseToUser;
import com.skripsi.mrizk.findingdosen.repository.transformer.MyProfileResponseToUser;
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
    private final IMyProfileRequest iMyProfileRequest;
    private final LoginResponseToUser loginResponseToUser;
    private final RegisterResponseToRegister registerResponseToRegister;
    private final MyProfileResponseToUser myProfileResponseToUser;
    private final SharedPrefsUserRepository sharedPrefsUserRepository;

    @Inject
    public UserRepository(ILoginRequest iLoginRequest, IRegisterRequest iRegisterRequest,
                          LoginResponseToUser loginResponseToUser, RegisterResponseToRegister registerResponseToRegister,
                          IMyProfileRequest iMyProfileRequest, SharedPrefsUserRepository sharedPrefsUserRepository,
                          MyProfileResponseToUser myProfileResponseToUser) {
        this.iLoginRequest = iLoginRequest;
        this.iRegisterRequest = iRegisterRequest;
        this.loginResponseToUser = loginResponseToUser;
        this.registerResponseToRegister = registerResponseToRegister;
        this.iMyProfileRequest = iMyProfileRequest;
        this.sharedPrefsUserRepository = sharedPrefsUserRepository;
        this.myProfileResponseToUser = myProfileResponseToUser;
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

    public Observable<User> myProfile() {
        String token = sharedPrefsUserRepository.getUserFromPrefs().getToken();
        return iMyProfileRequest.myProfile(token)
                .toObservable()
                .map(myProfileResponseToUser::transform)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
