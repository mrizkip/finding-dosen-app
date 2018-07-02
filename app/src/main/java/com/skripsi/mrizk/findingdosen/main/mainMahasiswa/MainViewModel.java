package com.skripsi.mrizk.findingdosen.main.mainMahasiswa;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.skripsi.mrizk.findingdosen.repository.datasource.local.DosenRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by mrizk on 17/03/2018.
 */

public class MainViewModel extends ViewModel {

    private static final String TAG = "MainViewModel";

    private final DosenRepository dosenRepository;
    private MutableLiveData<List<DosenAdapter>> listDosen;
    private final CompositeDisposable compositeDisposable;

    public MainViewModel(DosenRepository dosenRepository) {
        this.dosenRepository = dosenRepository;
        listDosen = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();

        fetchDosen();
    }

    private void fetchDosen() {
        Disposable disposable = dosenRepository.getListDosen()
                .subscribe(dosenList -> {
                    this.listDosen.postValue(dosenList);
                }, throwable -> {
                    Log.e(TAG, "fetchDosen: error");
                });
        compositeDisposable.add(disposable);
    }


    public LiveData<List<DosenAdapter>> getDosenList() {
        return listDosen;
    }


    public static class MainViewModelFactory implements ViewModelProvider.Factory {

        private final DosenRepository dosenRepository;

        @Inject
        public MainViewModelFactory(DosenRepository dosenRepository) {
            this.dosenRepository = dosenRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new MainViewModel(dosenRepository);
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
