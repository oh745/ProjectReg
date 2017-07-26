package com.example.preedaphongr.projectreg.register.presenter;

import android.util.Log;
import android.view.View;

import com.example.preedaphongr.projectreg.register.fragment.SearchCourseFragment;
import com.example.preedaphongr.projectreg.register.model.Course;
import com.example.preedaphongr.projectreg.register.model.CourseRequest;
import com.example.preedaphongr.projectreg.register.service.SearchCourseService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by preedaphong.r on 25/07/2560.
 */

public class SearchCoursePresenter {
    private View view;

    //@Inject
    SearchCourseService searchCourseService;

    public void setView(View view){
        this.view = view;
    }


    public void sendSearchCourseRequest(int majorId ,int semester){
        searchCourseService.loadCourse(new CourseRequest(majorId,semester)).enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                if(response.isSuccessful()){
                    Log.d("@@@","******************success********************");
                }
                else {
                    Log.d("@@@","******************unsuccess********************");
                }
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                Log.d("@@@","******************Fail********************");
            }
        });
    }
}
