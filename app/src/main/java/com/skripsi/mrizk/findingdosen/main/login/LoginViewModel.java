package com.skripsi.mrizk.findingdosen.main.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.skripsi.mrizk.findingdosen.repository.datasource.local.SharedPrefsUserRepository;
import com.skripsi.mrizk.findingdosen.repository.datasource.local.UserRepository;
import com.skripsi.mrizk.findingdosen.repository.entity.api.LoginRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.local.User;

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
    private MutableLiveData<User> loggedInUser;
    private final CompositeDisposable compositeDisposable;

    public LoginViewModel(UserRepository userRepository, SharedPrefsUserRepository sharedPrefsUserRepository) {
        this.userRepository = userRepository;
        this.sharedPrefsUserRepository = sharedPrefsUserRepository;
        loggedInUser = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
    }

    public LiveData<User> loginUser(String email, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        Disposable disposable = userRepository.loginUser(loginRequest)
                .subscribe(user1 -> {
                    this.loggedInUser.postValue(user1);
                    sharedPrefsUserRepository.saveUserToPrefs(user1);
                }, throwable -> {
                    this.loggedInUser.postValue(null);
                });
        compositeDisposable.add(disposable);
        return loggedInUser;
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
