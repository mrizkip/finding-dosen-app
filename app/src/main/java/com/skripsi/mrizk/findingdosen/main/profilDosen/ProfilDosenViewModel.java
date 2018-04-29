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

public class ProfilDosenViewModel extends ViewModel{

    private static final String TAG = "ProfilDosenViewModel";

    private final DosenRepository dosenRepository;
    private MutableLiveData<ProfilDosen> profilDosen;

    public ProfilDosenViewModel(DosenRepository dosenRepository) {
        this.dosenRepository = dosenRepository;
        profilDosen = new MutableLiveData<>();
    }

    public LiveData<ProfilDosen> getProfilDosen(int userId) {
        dosenRepository.getProfilDosen(userId)
                .subscribe(dosen -> {
                    profilDosen.postValue(dosen);
                }, throwable -> {
                    Log.e(TAG, "getProfilDosen: Error");
                });
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
}
