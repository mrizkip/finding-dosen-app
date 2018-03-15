package com.skripsi.mrizk.findingdosen.di.component;

import com.skripsi.mrizk.findingdosen.di.module.MapperModule;
import com.skripsi.mrizk.findingdosen.repository.transformer.ErrorMessageRemoteToErrorMessage;
import com.skripsi.mrizk.findingdosen.repository.transformer.ErrorUnauthorizedRemoteToErrorUnauthorized;
import com.skripsi.mrizk.findingdosen.repository.transformer.LoginResponseToUser;
import com.skripsi.mrizk.findingdosen.repository.transformer.RegisterResponseToRegister;

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

}
