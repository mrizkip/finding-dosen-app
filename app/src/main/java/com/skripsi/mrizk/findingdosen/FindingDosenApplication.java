package com.skripsi.mrizk.findingdosen;

import android.app.Application;

import com.skripsi.mrizk.findingdosen.di.component.DaggerIAppComponent;
import com.skripsi.mrizk.findingdosen.di.component.DaggerIDataComponent;
import com.skripsi.mrizk.findingdosen.di.component.DaggerIMapperComponent;
import com.skripsi.mrizk.findingdosen.di.component.IAppComponent;
import com.skripsi.mrizk.findingdosen.di.component.IDataComponent;
import com.skripsi.mrizk.findingdosen.di.component.IMapperComponent;
import com.skripsi.mrizk.findingdosen.di.module.AppModule;
import com.skripsi.mrizk.findingdosen.di.module.DataModule;
import com.skripsi.mrizk.findingdosen.di.module.MapperModule;

/**
 * Created by mrizk on 16/01/2018.
 */

public class FindingDosenApplication extends Application{

    private static IAppComponent applicationComponent;
    private static IDataComponent dataComponent;
    private static IMapperComponent mapperComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerIAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        dataComponent = DaggerIDataComponent.builder()
                .dataModule(new DataModule("http://192.168.100.16:8080/"))
                .build();

        mapperComponent = DaggerIMapperComponent.create();

    }

    public static IAppComponent getApplicationComponent() {return applicationComponent;}
    public static IDataComponent getDataComponent() { return dataComponent; }
    public static IMapperComponent getMapperComponent() { return mapperComponent; }
}
