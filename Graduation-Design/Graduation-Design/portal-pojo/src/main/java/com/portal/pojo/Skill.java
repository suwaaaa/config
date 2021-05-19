package com.portal.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "skill")
@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法
public class Skill implements Serializable {

    private Integer skill_id;

    private Integer resume_id;

    private String skill_name;

    private String skill_type;
}
