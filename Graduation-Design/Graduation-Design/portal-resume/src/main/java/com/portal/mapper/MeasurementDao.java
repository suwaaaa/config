package com.portal.mapper;

import com.portal.pojo.Measurement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MeasurementDao {

    Integer addOutsideUrl(Measurement measurement);

    Integer deleteOutsideUrlById(Integer id);

    Integer updateOutsideUrlById(Measurement measurement);

    Integer queryOutsideUrlById(Integer id);

    List<Measurement> queryOutsideUrlAll();



}
