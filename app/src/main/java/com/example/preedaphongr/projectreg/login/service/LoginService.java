package com.example.preedaphongr.projectreg.login.service;

import com.example.preedaphongr.projectreg.login.model.LoginRequest;
import com.example.preedaphongr.projectreg.login.model.LoginResponse;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by preedaphong.r on 01/08/2560.
 */

public class LoginService {
    private  LoginAPI loginAPI;

    @Inject
    public LoginService(LoginAPI loginAPI) {
        this.loginAPI = loginAPI;
    }
    public Call<LoginResponse> loadCourse(LoginRequest loginRequest){
        return loginAPI.getRegisterList(loginRequest);
    }
}
