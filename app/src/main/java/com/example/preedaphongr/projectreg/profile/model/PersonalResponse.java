package com.example.preedaphongr.projectreg.profile.model;

import com.example.preedaphongr.projectreg.register.model.Course;

import java.util.List;

/**
 * Created by preedaphong.r on 01/08/2560.
 */

public class PersonalResponse {
    private String stdId;
    private String firstName;
    private String lastName;
    private int majorId;
    private int educationLevel;
    private int currentTerm;
    private String majorName;
    private List<Course> courseListInfo;

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public List<Course> getCourseListInfo() {
        return courseListInfo;
    }

    public void setCourseListInfo(List<Course> courseListInfo) {
        this.courseListInfo = courseListInfo;
    }

    public int getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(int educationLevel) {
        this.educationLevel = educationLevel;
    }

    public int getCurrentTerm() {
        return currentTerm;
    }

    public void setCurrentTerm(int currentTerm) {
        this.currentTerm = currentTerm;
    }
}
