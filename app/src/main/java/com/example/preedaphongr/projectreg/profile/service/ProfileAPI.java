package com.example.preedaphongr.projectreg.profile.service;

import com.example.preedaphongr.projectreg.profile.model.PersonalRequest;
import com.example.preedaphongr.projectreg.profile.model.PersonalResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by preedaphong.r on 01/08/2560.
 */

public interface ProfileAPI {
    @POST("student/inqStudent")
    Call<PersonalResponse> getPersonalData(@Body PersonalRequest personalRequest);
}
