package com.application.testdoctalk.dagger.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.application.testdoctalk.BuildConfig;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Created by rkrde on 14-05-2017.
 */

@Module(includes = CacheModule.class)
public class NetworkModule {
    @Singleton
    @Provides
    public HttpLoggingInterceptor loggingInterceptor() {
        return new HttpLoggingInterceptor(msg->Timber.i(msg));
    }

    @Singleton
    @Provides
    public Interceptor interceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        };
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, Interceptor interceptor, Cache cache, StethoInterceptor stethoInterceptor) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(interceptor)
                .addNetworkInterceptor(stethoInterceptor)
                .build();
    }

    @Singleton
    @Provides
    StethoInterceptor stethoInterceptor() {
        return new StethoInterceptor();
    }
}