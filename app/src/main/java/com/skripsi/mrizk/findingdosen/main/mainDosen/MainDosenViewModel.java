package com.skripsi.mrizk.findingdosen.main.mainDosen;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.skripsi.mrizk.findingdosen.repository.datasource.local.DosenRepository;
import com.skripsi.mrizk.findingdosen.repository.datasource.local.SharedPrefsUserRepository;
import com.skripsi.mrizk.findingdosen.repository.entity.local.ProfilDosen;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainDosenViewModel extends ViewModel {

    private static final String TAG = "MainDosenViewModel";

    private final DosenRepository dosenRepository;
    private final SharedPrefsUserRepository sharedPrefsUserRepository;
    private MutableLiveData<ProfilDosen> profilDosen;
    private final CompositeDisposable compositeDisposable;

    public MainDosenViewModel(DosenRepository dosenRepository, SharedPrefsUserRepository sharedPrefsUserRepository) {
        this.dosenRepository = dosenRepository;
        this.sharedPrefsUserRepository = sharedPrefsUserRepository;
        this.profilDosen = new MutableLiveData<>();
        this.compositeDisposable = new CompositeDisposable();
        fetchProfil();
    }

    private void fetchProfil() {
        int userId = sharedPrefsUserRepository.getUserFromPrefs().getId();
        Disposable disposable = dosenRepository.getProfilDosen(userId)
                .subscribe(this.profilDosen::postValue, throwable -> {
                    Log.e(TAG, "getProfilDosen: Error");
                });
        compositeDisposable.add(disposable);
    }

    public LiveData<ProfilDosen> getProfilDosen() {
        return profilDosen;
    }

    public static class MainDosenViewModelFactory implements ViewModelProvider.Factory {

        private final DosenRepository dosenRepository;
        private final SharedPrefsUserRepository sharedPrefsUserRepository;

        @Inject
        public MainDosenViewModelFactory(DosenRepository dosenRepository, SharedPrefsUserRepository sharedPrefsUserRepository) {
            this.dosenRepository = dosenRepository;
            this.sharedPrefsUserRepository = sharedPrefsUserRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new MainDosenViewModel(dosenRepository, sharedPrefsUserRepository);
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
