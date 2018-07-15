package com.skripsi.mrizk.findingdosen.repository.datasource.local;

import com.skripsi.mrizk.findingdosen.repository.datasource.api.IRubahStatus;
import com.skripsi.mrizk.findingdosen.repository.entity.api.AccessPointRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.api.RubahLokasiRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.api.RubahLokasiResponse;
import com.skripsi.mrizk.findingdosen.repository.entity.api.RubahStatusRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.api.RubahStatusResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StatusRepository {

    private final IRubahStatus iRubahStatus;
    private final SharedPrefsUserRepository sharedPrefsUserRepository;

    @Inject
    public StatusRepository(IRubahStatus iRubahStatus, SharedPrefsUserRepository sharedPrefsUserRepository) {
        this.iRubahStatus = iRubahStatus;
        this.sharedPrefsUserRepository = sharedPrefsUserRepository;
    }

    public Observable<RubahStatusResponse> ubahStatus(String status, String keterangan) {
        String token = sharedPrefsUserRepository.getUserFromPrefs().getToken();
        int id = sharedPrefsUserRepository.getUserFromPrefs().getId();
        RubahStatusRequest request = new RubahStatusRequest();
        request.setUser_id(id);
        request.setStatus(status);
        request.setDesc_status(keterangan);

        return iRubahStatus.ubahStatus(token, request)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<RubahLokasiResponse> ubahLokasi(List<AccessPointRequest> listApRequest) {
        String token = sharedPrefsUserRepository.getUserFromPrefs().getToken();
        RubahLokasiRequest request = new RubahLokasiRequest();
        request.setData(listApRequest);

        return iRubahStatus.ubahLokasi(token, request)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
