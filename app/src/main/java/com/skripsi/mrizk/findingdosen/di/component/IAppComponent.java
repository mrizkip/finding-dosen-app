package com.skripsi.mrizk.findingdosen.di.component;

import android.content.Context;

import com.skripsi.mrizk.findingdosen.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

/**
 * Created by mrizk on 16/01/2018.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface IAppComponent {

    Context getApplicationContext();

}
