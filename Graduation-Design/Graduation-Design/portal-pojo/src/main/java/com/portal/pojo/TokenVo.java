package com.portal.pojo;

import java.io.Serializable;

public class TokenVo implements Serializable {
    private String token;
    private String currentIp;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCurrentIp() {
        return currentIp;
    }

    public void setCurrentIp(String currentIp) {
        this.currentIp = currentIp;
    }
}
