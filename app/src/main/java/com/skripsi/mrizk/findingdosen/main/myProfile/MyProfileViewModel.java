package com.skripsi.mrizk.findingdosen.main.myProfile;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.skripsi.mrizk.findingdosen.repository.datasource.local.UserRepository;
import com.skripsi.mrizk.findingdosen.repository.entity.local.User;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MyProfileViewModel extends ViewModel {
    private static final String TAG = "MyProfileViewModel";

    private final UserRepository userRepository;
    private MutableLiveData<User> userMutableLiveData;
    private final CompositeDisposable compositeDisposable;

    public MyProfileViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userMutableLiveData = new MutableLiveData<>();
        this.compositeDisposable = new CompositeDisposable();
        fetchMyProfile();
    }

    private void fetchMyProfile() {
        Disposable disposable = userRepository.myProfile()
                .subscribe(userMutableLiveData::postValue,
                        throwable -> Log.e(TAG, "getMyProfile: Error"));
        compositeDisposable.add(disposable);
    }

    public LiveData<User> getMyProfile() {
        return userMutableLiveData;
    }

    public static class MyProfileViewModelFactory implements ViewModelProvider.Factory {

        private final UserRepository userRepository;

        @Inject
        public MyProfileViewModelFactory(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new MyProfileViewModel(userRepository);
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
