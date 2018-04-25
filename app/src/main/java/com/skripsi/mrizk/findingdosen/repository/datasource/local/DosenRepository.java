package com.skripsi.mrizk.findingdosen.repository.datasource.local;

import android.util.Log;

import com.skripsi.mrizk.findingdosen.main.mainMahasiswa.DosenAdapter;
import com.skripsi.mrizk.findingdosen.repository.datasource.api.IFetchDosenRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.api.DosenListResponse;
import com.skripsi.mrizk.findingdosen.repository.transformer.FetchDosenResponseToDosenAdapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mrizk on 16/03/2018.
 */

public class DosenRepository {
    private static final String TAG = "DosenRepository";

    private final IFetchDosenRequest iFetchDosenRequest;
    private final FetchDosenResponseToDosenAdapter fetchDosenResponseToDosenAdapter;
    private final SharedPrefsUserRepository sharedPrefsUserRepository;

    @Inject
    public DosenRepository(IFetchDosenRequest iFetchDosenRequest, FetchDosenResponseToDosenAdapter fetchDosenResponseToDosenAdapter, SharedPrefsUserRepository sharedPrefsUserRepository) {
        this.iFetchDosenRequest = iFetchDosenRequest;
        this.fetchDosenResponseToDosenAdapter = fetchDosenResponseToDosenAdapter;
        this.sharedPrefsUserRepository = sharedPrefsUserRepository;
    }

    public Observable<List<DosenAdapter>> getListDosen() {
        String token = sharedPrefsUserRepository.getUserFromPrefs().getToken();
        return iFetchDosenRequest.fetchDosen(token)
                .toObservable()
                .map(fetchDosenResponseToDosenAdapter::transform)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
