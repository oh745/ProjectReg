package com.example.preedaphongr.projectreg.register.component;

import com.example.preedaphongr.projectreg.network.NetworkModule;
import com.example.preedaphongr.projectreg.register.activity.MainActivity;
import com.example.preedaphongr.projectreg.register.fragment.SearchCourseFragment;
import com.example.preedaphongr.projectreg.register.presenter.SearchCoursePresenter;
import com.example.preedaphongr.projectreg.register.service.SearchCourseService;

import dagger.Component;

/**
 * Created by preedaphong.r on 25/07/2560.
 */

@Component(modules = NetworkModule.class)
public interface SearchCourseComponent {

        void inject(MainActivity mainActivity);
        //void inject(SearchCourseFragment searchCourseFragment);
        //void inject(SearchCoursePresenter searchCoursePresenter);
        //void inject(SearchCourseService searchCourseService);
}
