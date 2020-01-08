package com.moon.libcommon.di;

import com.moon.libbase.dl.module.HttpClientModule;
import com.moon.libcommon.http.RetrofitFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author ry
 * @date 2019-12-13
 */
@Module()
public class CommonHttpModule {
    @Singleton
    @Provides
    public RetrofitFactory provideRetrofitFactory(OkHttpClient httpClient) {
        return new RetrofitFactory(httpClient);
    }
    @Singleton
    @Provides
    public Retrofit provideRetrofit(RetrofitFactory factory) {
        return factory.getRetrofit();
    }
}
