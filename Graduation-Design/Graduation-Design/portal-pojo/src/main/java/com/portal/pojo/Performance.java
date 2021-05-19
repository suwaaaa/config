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

@Table(name = "school_performance")
@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法
public class Performance implements Serializable {

    private Integer performance_id;

    private Integer resume_id;

    private String performance_name;

    private String performance_describtion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")//注解可以以该格式注入格式
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//输出格式
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date start_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")//注解可以以该格式注入格式
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//输出格式
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date end_date;

}
