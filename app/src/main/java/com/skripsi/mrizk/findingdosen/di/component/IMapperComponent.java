package com.skripsi.mrizk.findingdosen.di.component;

import com.skripsi.mrizk.findingdosen.di.module.MapperModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mrizk on 16/01/2018.
 */
@Singleton
@Component(modules = {MapperModule.class})
public interface IMapperComponent {
}
