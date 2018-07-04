package com.skripsi.mrizk.findingdosen.main.profilDosen;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.skripsi.mrizk.findingdosen.repository.datasource.local.DosenRepository;
import com.skripsi.mrizk.findingdosen.repository.entity.local.ProfilDosen;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class ProfilDosenViewModel extends ViewModel{

    private static final String TAG = "ProfilDosenViewModel";

    private final DosenRepository dosenRepository;
    private MutableLiveData<ProfilDosen> profilDosen;
    private final CompositeDisposable compositeDisposable;

    public ProfilDosenViewModel(DosenRepository dosenRepository) {
        this.dosenRepository = dosenRepository;
        profilDosen = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
    }

    public LiveData<ProfilDosen> getProfilDosen(int userId) {
        Disposable disposable = dosenRepository.getProfilDosen(userId)
                .subscribe(profilDosen::postValue, throwable -> {
                    Log.e(TAG, "getProfilDosen: Error");
                });
        compositeDisposable.add(disposable);
        return profilDosen;
    }

    public static class ProfilDosenViewModelFactory implements ViewModelProvider.Factory {

        private final DosenRepository dosenRepository;

        @Inject
        public ProfilDosenViewModelFactory(DosenRepository dosenRepository) {
            this.dosenRepository = dosenRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ProfilDosenViewModel(dosenRepository);
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
