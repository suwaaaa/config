package com.portal.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("注册实体")
public class RegisterVo implements Serializable {
        @ApiModelProperty("用户昵称")
        private String nickName;
        @ApiModelProperty("用户名")
        private String username;
        @ApiModelProperty("用户密码")
        private String password;
        @ApiModelProperty("短信验证码")
        private String smsCode;


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

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
        return "RegisterVo{" +
                "nickName='" + nickName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", smsCode='" + smsCode + '\'' +
                '}';
    }
}
