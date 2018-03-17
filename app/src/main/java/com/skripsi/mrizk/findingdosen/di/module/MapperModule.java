package com.skripsi.mrizk.findingdosen.di.module;

import com.skripsi.mrizk.findingdosen.repository.transformer.ErrorMessageRemoteToErrorMessage;
import com.skripsi.mrizk.findingdosen.repository.transformer.ErrorUnauthorizedRemoteToErrorUnauthorized;
import com.skripsi.mrizk.findingdosen.repository.transformer.FetchDosenResponseToUser;
import com.skripsi.mrizk.findingdosen.repository.transformer.LoginResponseToUser;
import com.skripsi.mrizk.findingdosen.repository.transformer.MyProfileResponseToUser;
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
    @Singleton
    LoginResponseToUser provideLoginResponseToUser() {
        return new LoginResponseToUser();
    }

    @Provides
    @Singleton
    ErrorMessageRemoteToErrorMessage provideErrorMessageRemoteToErrorMessage() {
        return new ErrorMessageRemoteToErrorMessage();
    }

    @Provides
    @Singleton
    ErrorUnauthorizedRemoteToErrorUnauthorized provideErrorUnauthorizedRemoteToErrorUnauthorized() {
        return new ErrorUnauthorizedRemoteToErrorUnauthorized();
    }

    @Provides
    @Singleton
    RegisterResponseToRegister provideRegisterResponseToRegister() {
        return new RegisterResponseToRegister();
    }

    @Provides
    @Singleton
    UserRemoteToUser provideUserRemoteToUser() {
        return new UserRemoteToUser();
    }

    @Provides
    @Singleton
    FetchDosenResponseToUser provideFetchDosenResponseToUser(UserRemoteToUser userRemoteToUser) {
        return new FetchDosenResponseToUser(userRemoteToUser);
    }



}
