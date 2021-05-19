package com.portal.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@ApiModel("用户实体")
@Table(name = "user")
public class User2 implements Serializable {
    @ApiModelProperty("用户 id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("用户真实姓名")
    private String userRealName;
    @ApiModelProperty("用户性别")
    private String userSex;
    @ApiModelProperty("用户地址")
    private String userAddress;
    @ApiModelProperty("用户年龄")
    private Integer userAge;
    @ApiModelProperty("用户创建时间")
    private Date userCreatime;
    @ApiModelProperty("用户手机")
    private String userPhone;
    @ApiModelProperty("用户密码")
    private String userPassword;
    @ApiModelProperty("用户邮箱")
    private String userEmail;
    @ApiModelProperty("毕业院校")
    private String  userSchool;
    @ApiModelProperty("出生日期")
    private Date userBirthday;
    @ApiModelProperty("学历")
    private String userEducational;
    @ApiModelProperty("头像地址")
    private String userPhotoUrl;
    @ApiModelProperty("盐")
    private String salt;
    @ApiModelProperty("用户是否可用")
    private Boolean active;
    @ApiModelProperty("角色")
    private Role role;



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

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Date getUserCreatime() {
        return userCreatime;
    }

    public void setUserCreatime(Date userCreatime) {
        this.userCreatime = userCreatime;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserEducational() {
        return userEducational;
    }

    public void setUserEducational(String userEducational) {
        this.userEducational = userEducational;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User2{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", userSex=" + userSex +
                ", userAddress='" + userAddress + '\'' +
                ", userAge=" + userAge +
                ", userCreatime=" + userCreatime +
                ", userPhone='" + userPhone + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userSchool='" + userSchool + '\'' +
                ", userBirthday=" + userBirthday +
                ", userEducational='" + userEducational + '\'' +
                ", userPhotoUrl='" + userPhotoUrl + '\'' +
                ", salt='" + salt + '\'' +
                ", active=" + active +
                ", role=" + role +
                '}';
    }
}