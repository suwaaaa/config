package com.portal.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("手机vo")
public class PhoneVo implements Serializable {
    @ApiModelProperty("用户手机")
    private String phone;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("验证码")
    private String codeText;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCodeText() {
        return codeText;
    }

    public void setCodeText(String codeText) {
        this.codeText = codeText;
    }


    @Override
    public String toString() {
        return "PhoneVo{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", codeText='" + codeText + '\'' +
                '}';
    }
}
