package com.portal.mapper;

import com.portal.pojo.Resumeposition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
//@Repository
public interface ResumepositionDao {

    List<Resumeposition> queryPositionAll();

    List<Resumeposition> queryPositionDelAll();

    List<Resumeposition> queryPositionPending();

    List<Resumeposition> queryPositionUrgent();

    List<Resumeposition> queryPositionById(Integer position_id);

    Integer countPositionAll();

    Integer countPositionDelAll();

    Integer countPositionByFive();

    List<Resumeposition> selectPositionFive(Resumeposition resumeposition);

    Boolean addPositionAll(Resumeposition resumeposition);

    Boolean addPositionSelective(Resumeposition resumeposition);

    Boolean updatePositionAllById(Integer position_id);

    Boolean updatePositionSelective(Resumeposition resumeposition);

    Boolean deletePosition(Integer position_id);

    Boolean deleteByIdList(List<Resumeposition> resumepositionlist);
}
