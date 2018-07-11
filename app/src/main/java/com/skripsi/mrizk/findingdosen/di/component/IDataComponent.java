package com.skripsi.mrizk.findingdosen.di.component;

import com.skripsi.mrizk.findingdosen.di.module.DataModule;
import com.skripsi.mrizk.findingdosen.main.editProfil.EditProfilViewModel;
import com.skripsi.mrizk.findingdosen.main.login.LoginViewModel;
import com.skripsi.mrizk.findingdosen.main.mainDosen.MainDosenViewModel;
import com.skripsi.mrizk.findingdosen.main.mainMahasiswa.MainViewModel;
import com.skripsi.mrizk.findingdosen.main.myProfile.MyProfileViewModel;
import com.skripsi.mrizk.findingdosen.main.posisiDosen.PosisiDosenViewModel;
import com.skripsi.mrizk.findingdosen.main.profilDosen.ProfilDosenViewModel;
import com.skripsi.mrizk.findingdosen.main.register.RegisterViewModel;
import com.skripsi.mrizk.findingdosen.repository.datasource.local.DosenRepository;
import com.skripsi.mrizk.findingdosen.repository.datasource.local.SharedPrefsUserRepository;
import com.skripsi.mrizk.findingdosen.repository.datasource.local.UserRepository;


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

    RegisterViewModel.RegisterViewModelFactory getRegisterViewModelFactory();

    DosenRepository getDosenRepository();

    MainViewModel.MainViewModelFactory getMainViewModelFactory();

    SharedPrefsUserRepository getSharedPrefsUserRepository();

    ProfilDosenViewModel.ProfilDosenViewModelFactory getProfilDosenViewModelFactory();

    PosisiDosenViewModel.PosisiDosenViewModelFactory getPosisiDosenViewModelFactory();

    MyProfileViewModel.MyProfileViewModelFactory getMyProfileViewModelFactory();

    MainDosenViewModel.MainDosenViewModelFactory getMainDosenViewModelFactory();

    EditProfilViewModel.EditProfilViewModelFactory getEditProfilViewModelFactory();
}
