package com.portal.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class UserForBase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private String userEducational;
    private Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEducational() {
        return userEducational;
    }

    public void setUserEducational(String userEducational) {
        this.userEducational = userEducational;
    }

    @Override
    public String toString() {
        return "UserForBase{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userEducational='" + userEducational + '\'' +
                ", active=" + active +
                '}';
    }
}
