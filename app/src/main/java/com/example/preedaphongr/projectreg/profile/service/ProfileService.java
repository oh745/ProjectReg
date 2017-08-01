package com.example.preedaphongr.projectreg.profile.service;

import com.example.preedaphongr.projectreg.profile.model.PersonalRequest;
import com.example.preedaphongr.projectreg.profile.model.PersonalResponse;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by preedaphong.r on 01/08/2560.
 */

public class ProfileService {
        private ProfileAPI profileAPI;

    @Inject
    public ProfileService(ProfileAPI profileAPI) {
        this.profileAPI = profileAPI;
    }

    public Call<PersonalResponse> loadPersonalData(PersonalRequest personalRequest){
        return profileAPI.getPersonalData(personalRequest);
    }
}
