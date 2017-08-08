package com.example.preedaphongr.projectreg.util;

import com.example.preedaphongr.projectreg.register.model.Course;

import java.util.HashMap;

/**
 * Created by preedaphong.r on 08/08/2560.
 */

public class RegisteredCourseEvent {
    public final HashMap<String,Boolean> course;

    public RegisteredCourseEvent(HashMap<String, Boolean> course) {
        this.course = course;
    }

}
