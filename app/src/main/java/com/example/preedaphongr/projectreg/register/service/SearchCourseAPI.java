package com.example.preedaphongr.projectreg.register.service;

import com.example.preedaphongr.projectreg.register.model.Course;
import com.example.preedaphongr.projectreg.register.model.CourseRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by preedaphong.r on 25/07/2560.
 */

public interface SearchCourseAPI {

    @POST("searchData")
    Call<Course> getCourseList(@Body CourseRequest courseRequest);
}
