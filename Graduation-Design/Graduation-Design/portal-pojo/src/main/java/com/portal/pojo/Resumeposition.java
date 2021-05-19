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
public class Resumeposition implements Serializable {

        private Integer position_id;//职位id

        private Integer recruit_status;//招聘状态

        private Integer position_numleft;//招聘人数

        private String position_location;//所在位置

        private String position_company;//所属公司

        private String technical_requirement;//技术要求

        private String position_name;//技术岗位

        private String work_salary;//工作资薪

        @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//注解可以以该格式注入格式
        @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")//输出格式
        @JsonSerialize(using = CustomDateSerializer.class)
        private Date release_time;//发布时间

        private Integer position_type;//招聘类型,0为没有，1表示为实习位置，2表示校招，3表示社招

        private Integer isdel;//删除标记

        public Integer getPosition_id() {
                return position_id;
        }

        public void setPosition_id(Integer position_id) {
                this.position_id = position_id;
        }

        public String getPosition_name() {
                return position_name;
        }

        public void setPosition_name(String position_name) {
                this.position_name = position_name;
        }

        public String getPosition_company() {
                return position_company;
        }

        public void setPosition_company(String position_company) {
                this.position_company = position_company;
        }

        public String getTechnical_requirement() {
                return technical_requirement;
        }

        public void setTechnical_requirement(String technical_requirement) {
                this.technical_requirement = technical_requirement;
        }

        public String getWork_salary() {
                return work_salary;
        }

        public void setWork_salary(String work_salary) {
                this.work_salary = work_salary;
        }

        public Integer getPosition_type() {
                return position_type;
        }

        public void setPosition_type(Integer position_type) {
                this.position_type = position_type;
        }
}

