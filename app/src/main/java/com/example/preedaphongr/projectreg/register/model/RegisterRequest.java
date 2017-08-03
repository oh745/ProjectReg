package com.example.preedaphongr.projectreg.register.model;

/**
 * Created by preedaphong.r on 02/08/2560.
 */

public class RegisterRequest {
    private String stdId;
    private String courseId;

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
