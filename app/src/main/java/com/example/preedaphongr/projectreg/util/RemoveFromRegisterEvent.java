package com.example.preedaphongr.projectreg.util;

import com.example.preedaphongr.projectreg.register.model.Course;

/**
 * Created by preedaphong.r on 27/07/2560.
 */

public class RemoveFromRegisterEvent {
    public final Course course;

    public RemoveFromRegisterEvent(Course course) {
        this.course = course;
    }
}
