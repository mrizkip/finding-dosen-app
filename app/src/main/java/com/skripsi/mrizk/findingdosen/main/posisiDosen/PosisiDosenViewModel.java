package com.skripsi.mrizk.findingdosen.main.posisiDosen;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.skripsi.mrizk.findingdosen.repository.datasource.local.DosenRepository;
import com.skripsi.mrizk.findingdosen.repository.entity.local.PosisiDosen;

import javax.inject.Inject;

public class PosisiDosenViewModel extends ViewModel {

    private static final String TAG = "PosisiDosenViewModel";

    private final DosenRepository dosenRepository;
    private MutableLiveData<PosisiDosen> posisiDosenLiveData;

    public PosisiDosenViewModel(DosenRepository dosenRepository) {
        this.dosenRepository = dosenRepository;
        posisiDosenLiveData = new MutableLiveData<>();
    }

    public LiveData<PosisiDosen> getPosisiDosen(int userId) {
        dosenRepository.getPosisiDosen(userId)
                .subscribe(posisiDosen -> {
                    posisiDosenLiveData.postValue(posisiDosen);
                }, throwable -> {
                    Log.e(TAG, "getPosisiDosen: Failded", throwable);
                });
        return posisiDosenLiveData;
    }

    public static class PosisiDosenViewModelFactory implements ViewModelProvider.Factory {

        private final DosenRepository dosenRepository;

        @Inject
        public PosisiDosenViewModelFactory(DosenRepository dosenRepository) {
            this.dosenRepository = dosenRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new PosisiDosenViewModel(dosenRepository);
        }
    }
}
