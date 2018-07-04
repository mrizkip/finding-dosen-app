package com.skripsi.mrizk.findingdosen.main.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.skripsi.mrizk.findingdosen.repository.datasource.local.SharedPrefsUserRepository;
import com.skripsi.mrizk.findingdosen.repository.datasource.local.UserRepository;
import com.skripsi.mrizk.findingdosen.repository.entity.api.LoginRequest;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by mrizk on 08/03/2018.
 */

public class LoginViewModel extends ViewModel {
    private static final String TAG = "LoginViewModel";

    private final UserRepository userRepository;
    private final SharedPrefsUserRepository sharedPrefsUserRepository;
    private MutableLiveData<Boolean> loginStatus;
    private final CompositeDisposable compositeDisposable;

    public LoginViewModel(UserRepository userRepository, SharedPrefsUserRepository sharedPrefsUserRepository) {
        this.userRepository = userRepository;
        this.sharedPrefsUserRepository = sharedPrefsUserRepository;
        loginStatus = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
    }

    public LiveData<Boolean> loginUser(String email, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        Disposable disposable = userRepository.loginUser(loginRequest)
                .subscribe(user1 -> {
                    this.loginStatus.postValue(true);
                    sharedPrefsUserRepository.saveUserToPrefs(user1);
                }, throwable -> {
                    this.loginStatus.postValue(false);
                });
        compositeDisposable.add(disposable);
        return loginStatus;
    }

    public static class LoginViewModelFactory implements ViewModelProvider.Factory {

        private final UserRepository userRepository;
        private final SharedPrefsUserRepository sharedPrefsUserRepository;

        @Inject
        public LoginViewModelFactory(UserRepository userRepository, SharedPrefsUserRepository sharedPrefsUserRepository) {
            this.userRepository = userRepository;
            this.sharedPrefsUserRepository = sharedPrefsUserRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new LoginViewModel(userRepository, sharedPrefsUserRepository);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
