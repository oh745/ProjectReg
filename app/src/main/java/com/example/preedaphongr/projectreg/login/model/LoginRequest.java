package com.example.preedaphongr.projectreg.login.model;

/**
 * Created by preedaphong.r on 01/08/2560.
 */

public class LoginRequest {
    private String stdId;
    private String password;

    public LoginRequest(String stdId, String password) {
        this.stdId = stdId;
        this.password = password;
    }

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
