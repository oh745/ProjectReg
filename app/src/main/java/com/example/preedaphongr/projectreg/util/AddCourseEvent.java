package com.example.preedaphongr.projectreg.util;

import com.example.preedaphongr.projectreg.register.model.Course;

/**
 * Created by preedaphong.r on 26/07/2560.
 */

public class AddCourseEvent {
    public final Course course;

    public AddCourseEvent(Course course) {
        this.course = course;
    }
}
