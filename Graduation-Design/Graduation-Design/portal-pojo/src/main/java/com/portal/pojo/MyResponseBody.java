package com.portal.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("MyResponseBody")
public class MyResponseBody implements Serializable {
    @ApiModelProperty("自定义响应码")
    private Integer code = 200;
    @ApiModelProperty("响应消息")
    private String message = "成功";
    @ApiModelProperty("响应数据")
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public MyResponseBody() {
    }

    public MyResponseBody(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public MyResponseBody(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static MyResponseBody  build(Integer code,String message,Object data){
        return  new MyResponseBody(code,message,data);
    }
    public static MyResponseBody  buildMessage(Integer code,String message){
        return  new MyResponseBody(code,message);
    }
}
