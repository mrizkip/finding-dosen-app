package com.skripsi.mrizk.findingdosen.repository.datasource.local;

import com.skripsi.mrizk.findingdosen.repository.datasource.api.IFetchDosenRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.User;
import com.skripsi.mrizk.findingdosen.repository.transformer.FetchDosenResponseToUser;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mrizk on 16/03/2018.
 */

public class DosenRepository {
    private static final String TAG = "DosenRepository";

    private final IFetchDosenRequest iFetchDosenRequest;
    private final FetchDosenResponseToUser fetchDosenResponseToUser;
    private final SharedPrefsUserRepository sharedPrefsUserRepository;

    @Inject
    public DosenRepository(IFetchDosenRequest iFetchDosenRequest, FetchDosenResponseToUser fetchDosenResponseToUser, SharedPrefsUserRepository sharedPrefsUserRepository) {
        this.iFetchDosenRequest = iFetchDosenRequest;
        this.fetchDosenResponseToUser = fetchDosenResponseToUser;
        this.sharedPrefsUserRepository = sharedPrefsUserRepository;
    }

    public Observable<List<User>> getListDosen() {
        String token = sharedPrefsUserRepository.getUserFromPrefs().getToken();
        return iFetchDosenRequest.fetchDosen(token)
                .toObservable()
                .map(fetchDosenResponseToUser::transform)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
