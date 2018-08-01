package com.skripsi.mrizk.findingdosen.di.component;

import com.skripsi.mrizk.findingdosen.di.module.MapperModule;
import com.skripsi.mrizk.findingdosen.repository.transformer.DosenListResponseToDosenAdapter;
import com.skripsi.mrizk.findingdosen.repository.transformer.ErrorMessageRemoteToErrorMessage;
import com.skripsi.mrizk.findingdosen.repository.transformer.ErrorUnauthorizedRemoteToErrorUnauthorized;
import com.skripsi.mrizk.findingdosen.repository.transformer.FetchDosenResponseToDosenAdapter;
import com.skripsi.mrizk.findingdosen.repository.transformer.LoginResponseToUser;
import com.skripsi.mrizk.findingdosen.repository.transformer.MyProfileResponseToUser;
import com.skripsi.mrizk.findingdosen.repository.transformer.PosisiDosenResponseToPosisiDosen;
import com.skripsi.mrizk.findingdosen.repository.transformer.ProfilDosenResponseToProfilDosen;
import com.skripsi.mrizk.findingdosen.repository.transformer.RegisterResponseToRegister;
import com.skripsi.mrizk.findingdosen.repository.transformer.UserRemoteToUser;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mrizk on 16/01/2018.
 */
@Singleton
@Component(modules = {MapperModule.class})
public interface IMapperComponent {

    LoginResponseToUser getLoginResponseToUser();

    ErrorUnauthorizedRemoteToErrorUnauthorized getErrorUnauthorizedRemoteToErrorUnauthorized();

    ErrorMessageRemoteToErrorMessage getErrorMessageRemoteToErrorMessage();

    RegisterResponseToRegister getRegisterResponseToRegister();

    UserRemoteToUser getUserRemoteToUser();

    FetchDosenResponseToDosenAdapter getFetchDosenResponseToUser();

    DosenListResponseToDosenAdapter getDosenListResponseToDosenAdapter();

    ProfilDosenResponseToProfilDosen getProfilDosenResponseToProfilDosen();

    PosisiDosenResponseToPosisiDosen getPosisiDosenResponseToPosisiDosen();

    MyProfileResponseToUser getMyProfileResponseToUser();

}
