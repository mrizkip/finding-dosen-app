package com.skripsi.mrizk.findingdosen.repository.datasource.local;

import com.skripsi.mrizk.findingdosen.main.mainMahasiswa.DosenAdapter;
import com.skripsi.mrizk.findingdosen.repository.datasource.api.IDosenProfileRequest;
import com.skripsi.mrizk.findingdosen.repository.datasource.api.IFetchDosenRequest;
import com.skripsi.mrizk.findingdosen.repository.entity.local.ProfilDosen;
import com.skripsi.mrizk.findingdosen.repository.transformer.FetchDosenResponseToDosenAdapter;
import com.skripsi.mrizk.findingdosen.repository.transformer.ProfilDosenResponseToProfilDosen;

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
    private final FetchDosenResponseToDosenAdapter fetchDosenResponseToDosenAdapter;
    private final SharedPrefsUserRepository sharedPrefsUserRepository;
    private final ProfilDosenResponseToProfilDosen profilDosenResponseToProfilDosen;
    private final IDosenProfileRequest iDosenProfileRequest;

    @Inject
    public DosenRepository(IFetchDosenRequest iFetchDosenRequest, FetchDosenResponseToDosenAdapter fetchDosenResponseToDosenAdapter, SharedPrefsUserRepository sharedPrefsUserRepository, ProfilDosenResponseToProfilDosen profilDosenResponseToProfilDosen, IDosenProfileRequest iDosenProfileRequest) {
        this.iFetchDosenRequest = iFetchDosenRequest;
        this.fetchDosenResponseToDosenAdapter = fetchDosenResponseToDosenAdapter;
        this.sharedPrefsUserRepository = sharedPrefsUserRepository;
        this.profilDosenResponseToProfilDosen = profilDosenResponseToProfilDosen;
        this.iDosenProfileRequest = iDosenProfileRequest;
    }

    public Observable<List<DosenAdapter>> getListDosen() {
        String token = sharedPrefsUserRepository.getUserFromPrefs().getToken();
        return iFetchDosenRequest.fetchDosen(token)
                .toObservable()
                .map(fetchDosenResponseToDosenAdapter::transform)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ProfilDosen> getProfilDosen(int userId) {
        String token = sharedPrefsUserRepository.getUserFromPrefs().getToken();
        return iDosenProfileRequest.getDosenProfile(token, userId)
                .toObservable()
                .map(profilDosenResponseToProfilDosen::transform)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
