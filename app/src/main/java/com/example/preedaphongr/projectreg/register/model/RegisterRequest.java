package com.example.preedaphongr.projectreg.register.model;

import java.util.List;

/**
 * Created by preedaphong.r on 02/08/2560.
 */

public class RegisterRequest {
    private String stdId;
    private List<String> courseId;

    public RegisterRequest(String stdId, List<String> courseId) {
        this.stdId = stdId;
        this.courseId = courseId;
    }

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public List<String> getCourseId() {
        return courseId;
    }

    public void setCourseId(List<String> courseId) {
        this.courseId = courseId;
    }
}
