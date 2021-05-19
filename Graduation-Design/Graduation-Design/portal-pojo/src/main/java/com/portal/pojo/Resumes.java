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

@Table(name = "resume")
@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法
public class Resumes implements Serializable {

    private Integer resume_id;//简历id

    private Integer user_id;//用户id

    private String self_evaluation;//自我评价

    private String additional_info;//附加信息

    private Integer state;//求职状态

    private Integer isdel;//是否删除

    private String resumepho_url;//用户简历的图片

    private String jobsearch;//寻求职位


    @DateTimeFormat(pattern = "yyyy-MM-dd")//注解可以以该格式注入格式
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//输出格式
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date resume_creatime;





    private List<User> users;

    private List<Skill> skills;

    private List<Performance> performances;

    private List<Experience> experiences;

    private List<Award> awards;








//    private String user_real_name;//用户名
//    private Integer user_sex;//用户性别,male为男性为1，famale为女性为2,没有填为0
//    private String user_address;//用户地址
//    private Integer user_age;//用户年龄
//    private Integer user_phone;//用户联系方式
//    private String user_email;//用户邮箱
//    private String user_educational;//用户教育程度
//
//
//    private String skill_name;
//    private String skill_type;
//
//
//    private String performance_name;
//    private String performance_describtion;
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//注解可以以该格式注入格式
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//输出格式
//    @JsonSerialize(using = CustomDateSerializer.class)
//    private Date start_date;
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//注解可以以该格式注入格式
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//输出格式
//    @JsonSerialize(using = CustomDateSerializer.class)
//    private Date end_date;
//
//
//    private String program_name;
//    private String program_describtion;
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//注解可以以该格式注入格式
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//输出格式
//    @JsonSerialize(using = CustomDateSerializer.class)
//    private Date startdate;
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//注解可以以该格式注入格式
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//输出格式
//    @JsonSerialize(using = CustomDateSerializer.class)
//    private Date enddate;
//
//    private String award_name;


    public String getResumepho_url() {
        return resumepho_url;
    }

    public void setResumepho_url(String resumepho_url) {
        this.resumepho_url = resumepho_url;
    }

    public Integer getResume_id() {
        return resume_id;
    }

    public void setResume_id(Integer resume_id) {
        this.resume_id = resume_id;
    }

    public Date getResume_creatime() {
        return resume_creatime;
    }

    public void setResume_creatime(Date resume_creatime) {
        this.resume_creatime = resume_creatime;
    }
}
