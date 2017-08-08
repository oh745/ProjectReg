package com.example.preedaphongr.projectreg.register.service;

import com.example.preedaphongr.projectreg.register.model.FacultyResponse;
import com.example.preedaphongr.projectreg.register.model.RegisterRequest;
import com.example.preedaphongr.projectreg.register.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by preedaphong.r on 02/08/2560.
 */

public interface RegisterAPI {
    @POST("register/courses")
    Call<RegisterResponse> sendCourse(@Body RegisterRequest registerRequest);
}
