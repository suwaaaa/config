package com.portal.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("邮箱vo")
public class EmailVo implements Serializable {
        @ApiModelProperty("用户邮箱")
        private String email;
        @ApiModelProperty("用户密码")
        private String password;
        @ApiModelProperty("验证码")
        private String codeText;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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
        return "EmailVo{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", codeText='" + codeText + '\'' +
                '}';
    }
}
