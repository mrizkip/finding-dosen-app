package com.skripsi.mrizk.findingdosen.di.module;

import com.skripsi.mrizk.findingdosen.FindingDosenApplication;
import com.skripsi.mrizk.findingdosen.repository.transformer.DosenListResponseToDosenAdapter;
import com.skripsi.mrizk.findingdosen.repository.transformer.ErrorMessageRemoteToErrorMessage;
import com.skripsi.mrizk.findingdosen.repository.transformer.ErrorUnauthorizedRemoteToErrorUnauthorized;
import com.skripsi.mrizk.findingdosen.repository.transformer.FetchDosenResponseToDosenAdapter;
import com.skripsi.mrizk.findingdosen.repository.transformer.LoginResponseToUser;
import com.skripsi.mrizk.findingdosen.repository.transformer.PosisiDosenResponseToPosisiDosen;
import com.skripsi.mrizk.findingdosen.repository.transformer.ProfilDosenResponseToProfilDosen;
import com.skripsi.mrizk.findingdosen.repository.transformer.RegisterResponseToRegister;
import com.skripsi.mrizk.findingdosen.repository.transformer.UserRemoteToUser;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mrizk on 16/01/2018.
 */
@Module
public class MapperModule {

    @Provides
    LoginResponseToUser provideLoginResponseToUser() {
        return new LoginResponseToUser();
    }

    @Provides
    ErrorMessageRemoteToErrorMessage provideErrorMessageRemoteToErrorMessage() {
        return new ErrorMessageRemoteToErrorMessage();
    }

    @Provides
    ErrorUnauthorizedRemoteToErrorUnauthorized provideErrorUnauthorizedRemoteToErrorUnauthorized() {
        return new ErrorUnauthorizedRemoteToErrorUnauthorized();
    }

    @Provides
    RegisterResponseToRegister provideRegisterResponseToRegister() {
        return new RegisterResponseToRegister();
    }

    @Provides
    UserRemoteToUser provideUserRemoteToUser() {
        return new UserRemoteToUser();
    }

    @Provides
    @Singleton
    DosenListResponseToDosenAdapter provideDosenListResponseToDosenAdapter() {
        return new DosenListResponseToDosenAdapter();
    }

    @Provides
    @Singleton
    FetchDosenResponseToDosenAdapter provideFetchDosenResponseToUser(DosenListResponseToDosenAdapter dosenListResponseToDosenAdapter) {
        return new FetchDosenResponseToDosenAdapter(dosenListResponseToDosenAdapter);
    }

    @Provides
    @Singleton
    ProfilDosenResponseToProfilDosen provideProfilDosenResponseToProfilDosen() {
        return new ProfilDosenResponseToProfilDosen();
    }

    @Provides
    @Singleton
    PosisiDosenResponseToPosisiDosen providePosisiDosenResponseToPosisiDosen() {
        return new PosisiDosenResponseToPosisiDosen();
    }

}
