package com.example.preedaphongr.projectreg.register.model;

/**
 * Created by preedaphong.r on 25/07/2560.
 */

public class CourseRequest {
    private int term;
    private int majorId;
    private int majorStd;

    public CourseRequest(int term, int majorId, int majorStd) {
        this.term = term;
        this.majorId = majorId;
        this.majorStd = majorStd;
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

    public int getMajorStd() {
        return majorStd;
    }

    public void setMajorStd(int majorStd) {
        this.majorStd = majorStd;
    }
}
