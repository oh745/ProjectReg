package com.example.preedaphongr.projectreg.profile.component;

import com.example.preedaphongr.projectreg.network.NetworkModule;
import com.example.preedaphongr.projectreg.profile.activity.ProfileActivity;

import dagger.Component;

/**
 * Created by preedaphong.r on 01/08/2560.
 */
@Component(modules = NetworkModule.class)
public interface ProfileComponent {
    void inject(ProfileActivity profileActivity);
}
