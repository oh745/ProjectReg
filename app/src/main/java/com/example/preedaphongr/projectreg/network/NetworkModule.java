package com.example.preedaphongr.projectreg.network;

import com.example.preedaphongr.projectreg.register.service.SearchCourseAPI;
import com.example.preedaphongr.projectreg.register.service.SearchCourseService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by preedaphong.r on 25/07/2560.
 */
@Module
public class NetworkModule {

    @Provides
    //@Singleton
    Retrofit provideRetrofit() {
        /*OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.215.96.78:8080/KSMEAPIWS/")
                //.client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
