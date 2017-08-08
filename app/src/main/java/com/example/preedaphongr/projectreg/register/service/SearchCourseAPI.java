package com.example.preedaphongr.projectreg.register.service;

import com.example.preedaphongr.projectreg.register.model.Course;
import com.example.preedaphongr.projectreg.register.model.CourseRequest;
import com.example.preedaphongr.projectreg.register.model.CourseResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by preedaphong.r on 25/07/2560.
 */

public interface SearchCourseAPI {

    @POST("course/inqCourseList")
    Call<CourseResponse> getCourseList(@Body CourseRequest courseRequest);
}
