package com.portal.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("更新邮箱vo")
public class UpdateEmailVo implements Serializable {
    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("更新后的邮箱")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UpdateEmailVo{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
