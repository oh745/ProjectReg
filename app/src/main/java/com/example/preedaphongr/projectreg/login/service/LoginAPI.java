package com.example.preedaphongr.projectreg.login.service;

import com.example.preedaphongr.projectreg.login.model.LoginRequest;
import com.example.preedaphongr.projectreg.login.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by preedaphong.r on 01/08/2560.
 */

public interface LoginAPI {

    @POST("loginData")
    Call<LoginResponse> getRegisterList(@Body LoginRequest loginRequest);
}
