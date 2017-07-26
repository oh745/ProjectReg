package com.example.preedaphongr.projectreg.register.service;

import com.example.preedaphongr.projectreg.register.model.Course;
import com.example.preedaphongr.projectreg.register.model.CourseRequest;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by preedaphong.r on 25/07/2560.
 */

public class SearchCourseService {
        private SearchCourseAPI searchCourseAPI;

    @Inject
    public SearchCourseService(SearchCourseAPI searchCourseAPI) {
        this.searchCourseAPI = searchCourseAPI;
    }
    public Call<Course> loadCourse(CourseRequest courseRequest){
        return searchCourseAPI.getCourseList(courseRequest);
    }
}
