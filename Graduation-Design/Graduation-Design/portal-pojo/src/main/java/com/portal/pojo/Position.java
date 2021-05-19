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

@Table(name = "position")
@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法
public class Position implements Serializable {

    private Integer position_id;//职位id

    private String technical_position;//技术岗位

    private String company;//所属公司

    private String technical_require;//技术要求

    private Integer position_type;//招聘类型,0为没有，1表示为实习位置，2表示校招，3表示社招

    private String work_salary;//工作资薪

    private String position_location;//所在位置

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//注解可以以该格式注入格式
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//输出格式
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date release_time;//发布时间


}
