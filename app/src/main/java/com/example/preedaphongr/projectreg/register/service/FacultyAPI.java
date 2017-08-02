package com.example.preedaphongr.projectreg.register.service;

import com.example.preedaphongr.projectreg.register.model.FacultyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by preedaphong.r on 02/08/2560.
 */

public interface FacultyAPI {
    @GET("facultyData")
    Call<FacultyResponse> getFacultyList();
}
