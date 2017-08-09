package com.example.preedaphongr.projectreg;

import android.app.Application;

import com.example.preedaphongr.projectreg.login.component.DaggerLoginComponent;
import com.example.preedaphongr.projectreg.login.component.LoginComponent;
import com.example.preedaphongr.projectreg.login.presenter.LoginPresenter;
import com.example.preedaphongr.projectreg.network.NetworkModule;
import com.example.preedaphongr.projectreg.profile.component.DaggerProfileComponent;
import com.example.preedaphongr.projectreg.profile.component.ProfileComponent;
import com.example.preedaphongr.projectreg.register.component.DaggerSearchCourseComponent;
import com.example.preedaphongr.projectreg.register.component.SearchCourseComponent;

import javax.inject.Inject;

/**
 * Created by preedaphong.r on 25/07/2560.
 */

public class BaseApplication extends Application {
    private SearchCourseComponent searchCourseComponent;
    private LoginComponent loginComponent;
    private ProfileComponent profileComponent;



    @Override
    public void onCreate() {
        super.onCreate();
        searchCourseComponent = DaggerSearchCourseComponent.builder()
                .networkModule(new NetworkModule())
                .build();
        loginComponent = DaggerLoginComponent.builder()
                .networkModule(new NetworkModule())
                .build();
        profileComponent = DaggerProfileComponent.builder()
                .networkModule(new NetworkModule())
                .build();

    }
    public SearchCourseComponent getSearchCourseComponent(){
        return searchCourseComponent;
    }

    public LoginComponent getLoginComponent() {
        return loginComponent;
    }

    public ProfileComponent getProfileComponent() {
        return profileComponent;
    }

}
