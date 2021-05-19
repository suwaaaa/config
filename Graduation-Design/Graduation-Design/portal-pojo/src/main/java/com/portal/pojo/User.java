package com.portal.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "user")
@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法
public class User implements Serializable {

    private Integer user_id;//用户id

    private String user_real_name;//用户名

    private String user_name;

    private Integer user_sex;//用户性别,male为男性为1，famale为女性为2,没有填为0

    private String user_address;//用户地址

    private Integer user_age;//用户年龄

    @DateTimeFormat(pattern = "yyyy-MM-dd")//注解可以以该格式注入格式
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//输出格式
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date user_creatime;//用户创建时间

    private String user_phone;//用户联系方式

    private String user_email;//用户邮箱

    private String user_photo_url;//用户头像地址连接

    private String user_educational;//用户教育程度

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_real_name() {
        return user_real_name;
    }

    public void setUser_real_name(String user_real_name) {
        this.user_real_name = user_real_name;
    }

    public String getUser_photo_url() {
        return user_photo_url;
    }

    public void setUser_photo_url(String user_photo_url) {
        this.user_photo_url = user_photo_url;
    }
}
