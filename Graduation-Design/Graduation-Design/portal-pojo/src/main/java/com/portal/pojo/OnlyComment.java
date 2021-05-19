package com.portal.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Table(name = "comment")
@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法
public class OnlyComment {

    public Integer comment_id;

    private Integer position_id;

    public Integer parent_id;

    private Integer user_id;

    private String comment_description;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//注解可以以该格式注入格式
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//输出格式
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date comment_creatime;

    private String commentpho_url;

    private String user_comment;

    private Integer comment_score;

    private Integer isdel;

    public List<OnlyComment> child;
}
