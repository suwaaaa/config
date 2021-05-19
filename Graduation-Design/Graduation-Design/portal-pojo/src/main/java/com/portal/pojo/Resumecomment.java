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
import java.util.List;

@Table(name = "comment")
@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法
public class Resumecomment implements Serializable {

    public Integer comment_id;

    private Integer position_id;

    public Integer parent_id;

    private Integer user_id;

    private String comment_description;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//注解可以以该格式注入格式
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//输出格式
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date comment_creatime;

    private String commentpho_url;

    private String user_comment;

    private Integer comment_score;

    private Integer isdel;

    public List<Resumecomment> child;

    private List<Resumeposition> positions;

    private List<User> users;

    public Integer getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getComment_creatime() {
        return comment_creatime;
    }

    public String getComment_description() {
        return comment_description;
    }

    public void setComment_description(String comment_description) {
        this.comment_description = comment_description;
    }

    public String getUser_comment() {
        return user_comment;
    }

    public void setUser_comment(String user_comment) {
        this.user_comment = user_comment;
    }

    public void setComment_creatime(Date comment_creatime) {
        this.comment_creatime = comment_creatime;
    }
}
