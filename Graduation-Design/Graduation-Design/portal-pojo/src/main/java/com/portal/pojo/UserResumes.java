package com.portal.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法
public class UserResumes implements Serializable {

    private Integer user_id;//用户id

    private String user_real_name;//用户名

    private Integer user_sex;//用户性别,male为男性为1，famale为女性为2,没有填为0

    private String user_address;//用户地址

    private Integer user_age;//用户年龄

//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//注解可以以该格式注入格式
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//输出格式
//    @JsonSerialize(using = CustomDateSerializer.class)
//    private Date user_creatime;//用户创建时间

    private Integer user_phone;//用户联系方式

    private String user_email;//用户邮箱

//    private String user_photo_url;//用户头像地址连接

    private String user_educational;//用户教育程度

    private List<Resumes> resumes;

}
