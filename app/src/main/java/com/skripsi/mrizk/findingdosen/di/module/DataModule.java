package com.skripsi.mrizk.findingdosen.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skripsi.mrizk.findingdosen.repository.datasource.api.IDosenProfileRequest;
import com.skripsi.mrizk.findingdosen.repository.datasource.api.IFetchDosenRequest;
import com.skripsi.mrizk.findingdosen.repository.datasource.api.ILoginRequest;
import com.skripsi.mrizk.findingdosen.repository.datasource.api.IRegisterRequest;
import com.skripsi.mrizk.findingdosen.repository.datasource.local.DosenRepository;
import com.skripsi.mrizk.findingdosen.repository.datasource.local.SharedPrefsUserRepository;
import com.skripsi.mrizk.findingdosen.repository.transformer.FetchDosenResponseToDosenAdapter;
import com.skripsi.mrizk.findingdosen.repository.transformer.ProfilDosenResponseToProfilDosen;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mrizk on 16/01/2018.
 */
@Module
public class DataModule {

    private final String baseUrl;

    public DataModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .create();
    }

    @Provides
    @Singleton
    public GsonConverterFactory provideGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    public RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);

        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory,
                                    RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
                                    OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build();
    }

    @Provides
    public ILoginRequest provideLoginRequest(Retrofit retrofit) {
        return retrofit.create(ILoginRequest.class);
    }

    @Provides
    public IRegisterRequest provideRegisterRequest(Retrofit retrofit) {
        return retrofit.create(IRegisterRequest.class);
    }

    @Provides
    @Singleton
    public SharedPrefsUserRepository provideSharedPrefsUserRepository() {
        return new SharedPrefsUserRepository();
    }

    @Provides
    @Singleton
    public IFetchDosenRequest provideFetchDosenRequest(Retrofit retrofit) {
        return retrofit.create(IFetchDosenRequest.class);
    }

    @Provides
    @Singleton
    public IDosenProfileRequest provideDosenProfileRequest(Retrofit retrofit) {
        return retrofit.create(IDosenProfileRequest.class);
    }

    @Provides
    @Singleton
    public DosenRepository provideDosenRepository(IFetchDosenRequest iFetchDosenRequest,
                                                  FetchDosenResponseToDosenAdapter dosenResponseToDosenAdapter,
                                                  SharedPrefsUserRepository sharedPrefsUserRepository,
                                                  ProfilDosenResponseToProfilDosen profilDosenResponseToProfilDosen,
                                                  IDosenProfileRequest iDosenProfileRequest) {
        return new DosenRepository(iFetchDosenRequest, dosenResponseToDosenAdapter, sharedPrefsUserRepository, profilDosenResponseToProfilDosen, iDosenProfileRequest);
    }

}