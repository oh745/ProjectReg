package com.example.preedaphongr.projectreg.login.presenter;

import android.util.Log;

import com.example.preedaphongr.projectreg.login.service.LoginService;

import javax.inject.Inject;

/**
 * Created by preedaphong.r on 01/08/2560.
 */

public class LoginPresenter {
    private LoginService loginService;

    @Inject
    public LoginPresenter() {

    }

    public void setPresenter(){

        Log.d("@@@","login presenter");
    }
}
