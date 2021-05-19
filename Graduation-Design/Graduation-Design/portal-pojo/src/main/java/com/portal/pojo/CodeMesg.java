package com.portal.pojo;

public enum CodeMesg {
    INFONULL(5000,"用户名密码不能为空"),
    USERNOEXIT(5001,"用户不存在"),
    PASSWORDERROR(5002,"账号密码不匹配"),
    PHONEPASSERROR(5003,"手机号格式不正确"),
    CODEEOOR(5004,"验证码不正确"),
    LOGINSUCCESS(5005,"登录成功"),
    EMAILPASSERROR(5006,"邮箱格式不正确"),

    //注册
    REGISTERINFONULL(5007,"手机号密码不能为空"),
    USEREXITERROR(5008,"用户已存在"),
    REGISTERSUCCESS(5009,"注册成功"),

    //更新
    UPDATESUCCESS(5010,"更新成功"),
    UPDATEFAILE(5011,"更新失败"),
    EMAILEXITERROR(5012,"该邮箱已被占用");

    private int code;
    private String message;


    CodeMesg(int code,String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
