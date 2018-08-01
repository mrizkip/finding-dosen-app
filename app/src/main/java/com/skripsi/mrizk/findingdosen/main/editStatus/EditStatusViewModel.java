package com.skripsi.mrizk.findingdosen.main.editStatus;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.skripsi.mrizk.findingdosen.repository.datasource.local.StatusRepository;
import com.skripsi.mrizk.findingdosen.repository.entity.api.AccessPointRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.api.RubahLokasiResponse;
import com.skripsi.mrizk.findingdosen.repository.entity.api.RubahStatusResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class EditStatusViewModel extends ViewModel {

    private final StatusRepository statusRepository;
    private MutableLiveData<RubahStatusResponse> rubahStatusResponse;
    private MutableLiveData<RubahLokasiResponse> rubahLokasiResponse;
    private final CompositeDisposable compositeDisposable;

    public EditStatusViewModel(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
        rubahStatusResponse = new MutableLiveData<>();
        rubahLokasiResponse = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
    }

    public LiveData<RubahStatusResponse> ubahStatus(String status, String keterangan) {
        Disposable disposable = statusRepository.ubahStatus(status, keterangan)
                .subscribe(rubahStatusResponse::postValue, throwable -> {
                    rubahStatusResponse.postValue(null);
                });
        compositeDisposable.add(disposable);
        return rubahStatusResponse;
    }

    public LiveData<RubahLokasiResponse> ubahLokasi(List<AccessPointRequest> accessPointRequests) {
        Disposable disposable = statusRepository.ubahLokasi(accessPointRequests)
                .subscribe(rubahLokasiResponse::postValue, throwable -> {
                    rubahLokasiResponse.postValue(null);
                });
        compositeDisposable.add(disposable);
        return rubahLokasiResponse;
    }

    public static class EditStatusViewModelFactory implements ViewModelProvider.Factory {

        private final StatusRepository statusRepository;

        @Inject
        public EditStatusViewModelFactory(StatusRepository statusRepository) {
            this.statusRepository = statusRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new EditStatusViewModel(statusRepository);
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
