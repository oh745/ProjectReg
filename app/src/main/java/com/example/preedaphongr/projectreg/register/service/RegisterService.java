package com.example.preedaphongr.projectreg.register.service;

import com.example.preedaphongr.projectreg.register.model.RegisterRequest;
import com.example.preedaphongr.projectreg.register.model.RegisterResponse;

import retrofit2.Call;

/**
 * Created by preedaphong.r on 02/08/2560.
 */

public class RegisterService {
    private RegisterAPI registerAPI;

    public RegisterService(RegisterAPI registerAPI) {
        this.registerAPI = registerAPI;
    }

    public Call<RegisterResponse> getRegisterResponse(RegisterRequest registerRequest){
        return registerAPI.sendCourse(registerRequest);
    }
}
