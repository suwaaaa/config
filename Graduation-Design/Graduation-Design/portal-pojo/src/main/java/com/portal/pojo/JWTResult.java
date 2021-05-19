package com.portal.pojo;

import java.io.Serializable;
import java.util.Map;

public class JWTResult implements Serializable {
    private String status;
    private Map<String,Object> userInfo;
    private String msg;
    private int code;

    public JWTResult() {
        super();
    }

    public JWTResult(String status, Map<String,Object> userInfo, String msg, int code) {
        super();
        this.status = status;
        this.userInfo = userInfo;
        this.msg = msg;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String,Object> getuserInfo() {
        return userInfo;
    }

    public void setuserInfo(Map<String,Object> userInfo) {
        this.userInfo = userInfo;
    }
}
