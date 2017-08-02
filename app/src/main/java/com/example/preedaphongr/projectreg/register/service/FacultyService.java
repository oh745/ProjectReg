package com.example.preedaphongr.projectreg.register.service;

import com.example.preedaphongr.projectreg.register.model.FacultyResponse;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by preedaphong.r on 02/08/2560.
 */

public class FacultyService {
    private FacultyAPI facultyAPI;

    @Inject
    public FacultyService(FacultyAPI facultyAPI) {
        this.facultyAPI = facultyAPI;
    }
    public Call<FacultyResponse> loadFaculty(){
        return facultyAPI.getFacultyList();
    }
}
