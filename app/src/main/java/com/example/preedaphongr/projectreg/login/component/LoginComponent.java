package com.example.preedaphongr.projectreg.login.component;

import com.example.preedaphongr.projectreg.login.activity.LoginActivity;
import com.example.preedaphongr.projectreg.network.NetworkModule;

import dagger.Component;

/**
 * Created by preedaphong.r on 01/08/2560.
 */
@Component(modules = NetworkModule.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
