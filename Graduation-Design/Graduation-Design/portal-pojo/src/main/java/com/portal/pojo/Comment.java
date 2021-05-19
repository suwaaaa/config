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

@Table(name = "comment")
@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法
public class Comment implements Serializable {

    private Integer comment_id;

    private String comment_description;

    private String comment_pho_url;

    private String position_comment;

    private String comment_outside_url;

    private Integer comment_score;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//注解可以以该格式注入格式
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//输出格式
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date  comment_creatime;




    private String technical_position;//技术岗位
    private String position_location;//所在位置

    private String user_real_name;//用户真实姓名
    private String user_email;//用户邮箱
    private String user_photo_url;//用户头像地址连接


}
