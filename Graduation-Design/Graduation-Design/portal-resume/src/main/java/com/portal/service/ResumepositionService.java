package com.portal.service;

import com.portal.pojo.Resumeposition;

import java.util.List;

public interface ResumepositionService {

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
