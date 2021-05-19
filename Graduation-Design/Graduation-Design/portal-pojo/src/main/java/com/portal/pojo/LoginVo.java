package com.portal.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

//登陆的实体类
@ApiModel("登录参数")
public class LoginVo implements Serializable {
    @ApiModelProperty("账号")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("用户昵称")
    private String smsCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", smsCode='" + smsCode + '\'' +
                '}';
    }
}
