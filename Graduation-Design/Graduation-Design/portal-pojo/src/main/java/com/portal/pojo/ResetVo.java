package com.portal.pojo;

import java.io.Serializable;

public class ResetVo implements Serializable {
    private String username;
    private String newPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "ResetVo{" +
                "username='" + username + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
