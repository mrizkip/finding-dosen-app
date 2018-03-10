package com.skripsi.mrizk.findingdosen.di.component;

import com.skripsi.mrizk.findingdosen.di.module.DataModule;
import com.skripsi.mrizk.findingdosen.main.login.LoginViewModel;
import com.skripsi.mrizk.findingdosen.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mrizk on 16/01/2018.
 */

@Singleton
@Component(modules = {DataModule.class})
public interface IDataComponent {

    LoginViewModel.LoginViewModelFactory getLoginViewModelFactory();

    UserRepository getUserRepository();

}
