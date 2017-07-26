package com.example.preedaphongr.projectreg.register.model;

/**
 * Created by preedaphong.r on 25/07/2560.
 */

public class CourseRequest {
    private int term;
    private int majorId;

    public CourseRequest(int term, int majorId) {
        this.term = term;
        this.majorId = majorId;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }
}
