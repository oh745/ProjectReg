package com.example.preedaphongr.projectreg.network;

import com.example.preedaphongr.projectreg.register.service.SearchCourseAPI;
import com.example.preedaphongr.projectreg.register.service.SearchCourseService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.215.136.167/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
