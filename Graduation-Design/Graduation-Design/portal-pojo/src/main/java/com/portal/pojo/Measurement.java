package com.portal.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "measurement")
@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法
public class Measurement implements Serializable {

    private Integer measurement_id;

    private String outside_url;

    private String name;
}
