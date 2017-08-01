package com.example.preedaphongr.projectreg.profile.model;

import com.example.preedaphongr.projectreg.login.model.*;

import java.util.List;

/**
 * Created by preedaphong.r on 01/08/2560.
 */

public class PersonalResponse {
    private String stdId;
    private String firstName;
    private String lastName;
    private String majorId;
    private String majorName;
    private List<EnrolledCourse> registeredList;

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

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public List<EnrolledCourse> getRegisteredList() {
        return registeredList;
    }

    public void setRegisteredList(List<EnrolledCourse> registeredList) {
        this.registeredList = registeredList;
    }
}
