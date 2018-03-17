package com.skripsi.mrizk.findingdosen.main.mainMahasiswa;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.skripsi.mrizk.findingdosen.repository.datasource.local.DosenRepository;
import com.skripsi.mrizk.findingdosen.repository.datasource.local.SharedPrefsUserRepository;
import com.skripsi.mrizk.findingdosen.repository.entity.User;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by mrizk on 17/03/2018.
 */

public class MainViewModel extends ViewModel {

    private final DosenRepository dosenRepository;
    private final SharedPrefsUserRepository sharedPrefsUserRepository;
    private MutableLiveData<List<User>> listDosen;

    public MainViewModel(DosenRepository dosenRepository, SharedPrefsUserRepository sharedPrefsUserRepository) {
        this.dosenRepository = dosenRepository;
        this.sharedPrefsUserRepository = sharedPrefsUserRepository;
    }

    public static class MainViewModelFactory implements ViewModelProvider.Factory {

        private final DosenRepository dosenRepository;
        private final SharedPrefsUserRepository sharedPrefsUserRepository;

        @Inject
        public MainViewModelFactory(DosenRepository dosenRepository, SharedPrefsUserRepository sharedPrefsUserRepository) {
            this.dosenRepository = dosenRepository;
            this.sharedPrefsUserRepository = sharedPrefsUserRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new MainViewModel(dosenRepository, sharedPrefsUserRepository);
        }
    }


}
