package com.example.preedaphongr.projectreg;

import android.app.Application;

import com.example.preedaphongr.projectreg.network.NetworkModule;
import com.example.preedaphongr.projectreg.register.component.DaggerSearchCourseComponent;
import com.example.preedaphongr.projectreg.register.component.SearchCourseComponent;

/**
 * Created by preedaphong.r on 25/07/2560.
 */

public class BaseApplication extends Application {
    private SearchCourseComponent searchCourseComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        searchCourseComponent = DaggerSearchCourseComponent.builder()
                .networkModule(new NetworkModule())
                .build();

    }
    public SearchCourseComponent getSearchCourseComponent(){
        return searchCourseComponent;
    }
}
