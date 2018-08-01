package com.skripsi.mrizk.findingdosen.main.editProfil;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.skripsi.mrizk.findingdosen.repository.datasource.local.UserRepository;
import com.skripsi.mrizk.findingdosen.repository.entity.api.EditProfilResponse;
import com.skripsi.mrizk.findingdosen.repository.entity.local.ProfilDosen;
import com.skripsi.mrizk.findingdosen.repository.entity.local.User;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class EditProfilViewModel extends ViewModel {

    private final UserRepository userRepository;
    private MutableLiveData<User> userLiveData;
    private MutableLiveData<EditProfilResponse> editProfilResponseLiveData;
    private final CompositeDisposable compositeDisposable;

    public EditProfilViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userLiveData = new MutableLiveData<>();
        this.editProfilResponseLiveData = new MutableLiveData<>();
        this.compositeDisposable = new CompositeDisposable();

        fetchProfile();
    }

    private void fetchProfile() {
        Disposable disposable = userRepository.myProfile()
                .subscribe(userLiveData::postValue, throwable -> userLiveData.postValue(null));
        compositeDisposable.add(disposable);
    }

    public LiveData<User> getProfil() {
        return userLiveData;
    }

    public LiveData<EditProfilResponse> editProfil(String noTelpon) {
        Disposable disposable = userRepository.editProfil(noTelpon)
                .subscribe(editProfilResponseLiveData::postValue, throwable -> editProfilResponseLiveData.postValue(null));
        compositeDisposable.add(disposable);
        return editProfilResponseLiveData;
    }

    public static class EditProfilViewModelFactory implements ViewModelProvider.Factory {

        private final UserRepository userRepository;

        @Inject
        public EditProfilViewModelFactory(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new EditProfilViewModel(userRepository);
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
