package com.portal.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "award")
@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法
public class Award implements Serializable {

    private Integer award_id;

    private Integer resume_id;

    private String award_name;
}
