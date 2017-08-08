package com.example.preedaphongr.projectreg.register.presenter;

import android.util.Log;
import android.view.View;

import com.example.preedaphongr.projectreg.register.fragment.SearchCourseFragment;
import com.example.preedaphongr.projectreg.register.model.Course;
import com.example.preedaphongr.projectreg.register.model.CourseRequest;
import com.example.preedaphongr.projectreg.register.model.CourseResponse;
import com.example.preedaphongr.projectreg.register.service.SearchCourseService;

import java.util.List;

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

    public interface View{
        public void setAdapter(CourseResponse courseResponse);
    }


    public void sendSearchCourseRequest(int majorId ,int semester,int majorStd){
        searchCourseService.loadCourse(new CourseRequest(majorId,semester,majorStd)).enqueue(new Callback<CourseResponse>() {
            @Override
            public void onResponse(Call<CourseResponse> call, Response<CourseResponse> response) {
                if(response.isSuccessful()){
                    Log.d("@@@","******************success********************");
                    view.setAdapter(response.body());
                }
                else {
                    Log.d("@@@","******************unsuccess********************");
                }
            }

            @Override
            public void onFailure(Call<CourseResponse> call, Throwable t) {
                Log.d("@@@","******************Fail********************");
            }
        });
    }
}
