package com.skripsi.mrizk.findingdosen;

import android.app.Application;

import com.skripsi.mrizk.findingdosen.di.component.DaggerIAppComponent;
import com.skripsi.mrizk.findingdosen.di.component.DaggerIDataComponent;
import com.skripsi.mrizk.findingdosen.di.component.IAppComponent;
import com.skripsi.mrizk.findingdosen.di.component.IDataComponent;
import com.skripsi.mrizk.findingdosen.di.module.AppModule;
import com.skripsi.mrizk.findingdosen.di.module.DataModule;

/**
 * Created by mrizk on 16/01/2018.
 */

public class FindingDosenApplication extends Application{

    private static IAppComponent applicationComponent;
    private static IDataComponent dataComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerIAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        dataComponent = DaggerIDataComponent.builder()
                .dataModule(new DataModule("http://127.0.0.1"))
                .build();
    }

    public static IAppComponent getApplicationComponent() {
        return applicationComponent;
    }
    public static IDataComponent getDataComponent() { return dataComponent; }

}
