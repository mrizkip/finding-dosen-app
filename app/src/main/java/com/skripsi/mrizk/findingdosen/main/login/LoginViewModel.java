package com.skripsi.mrizk.findingdosen.main.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.skripsi.mrizk.findingdosen.FindingDosenApplication;
import com.skripsi.mrizk.findingdosen.repository.UserRepository;
import com.skripsi.mrizk.findingdosen.repository.entity.ErrorMessage;
import com.skripsi.mrizk.findingdosen.repository.entity.User;
import com.skripsi.mrizk.findingdosen.repository.entity.api.ErrorMessageRemote;
import com.skripsi.mrizk.findingdosen.repository.entity.api.LoginRequest;
import com.skripsi.mrizk.findingdosen.repository.transformer.ErrorMessageRemoteToErrorMessage;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;

/**
 * Created by mrizk on 08/03/2018.
 */

public class LoginViewModel extends ViewModel {
    private static final String TAG = "LoginViewModel";

    private final UserRepository userRepository;
    private final ErrorMessageRemoteToErrorMessage errorMessageRemoteToErrorMessage;
    private MutableLiveData<User> user;
    private MutableLiveData<Boolean> loginStatus;

    public LoginViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        errorMessageRemoteToErrorMessage = FindingDosenApplication.getMapperComponent().getErrorMessageRemoteToErrorMessage();
        user = new MutableLiveData<>();
        loginStatus = new MutableLiveData<>();
    }

    public LiveData<Boolean> loginUser(String email, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        userRepository.loginUser(loginRequest)
                .subscribe(user1 -> {
                    this.loginStatus.postValue(true);
                    // TODO: Save user to pref
                    this.user.postValue(user1);
                }, throwable -> {
                    this.loginStatus.postValue(false);
                });

        return loginStatus;
    }

    public static class LoginViewModelFactory implements ViewModelProvider.Factory {

        private final UserRepository userRepository;

        @Inject
        public LoginViewModelFactory(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new LoginViewModel(userRepository);
        }
    }
}
