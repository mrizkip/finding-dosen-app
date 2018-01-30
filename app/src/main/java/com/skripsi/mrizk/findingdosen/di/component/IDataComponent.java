package com.skripsi.mrizk.findingdosen.di.component;

import com.skripsi.mrizk.findingdosen.di.module.DataModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mrizk on 16/01/2018.
 */

@Singleton
@Component(modules = {DataModule.class})
public interface IDataComponent {


}
