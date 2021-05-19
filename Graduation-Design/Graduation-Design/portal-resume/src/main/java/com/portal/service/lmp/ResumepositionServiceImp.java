package com.portal.service.lmp;

import com.portal.mapper.ResumepositionDao;
import com.portal.pojo.Resumeposition;
import com.portal.service.ResumepositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumepositionServiceImp  implements ResumepositionService{
    @Autowired
    private ResumepositionDao resumepositionDao;

    @Override
    public List<Resumeposition> queryPositionAll() {
        return resumepositionDao.queryPositionAll();
    }

    @Override
    public List<Resumeposition> queryPositionDelAll() {
        return resumepositionDao.queryPositionDelAll();
    }

    @Override
    public List<Resumeposition> queryPositionPending() {
        return resumepositionDao.queryPositionPending();
    }

    @Override
    public List<Resumeposition> queryPositionUrgent() {
        return resumepositionDao.queryPositionUrgent();
    }

    @Override
    public List<Resumeposition> queryPositionById(Integer position_id) {
        return resumepositionDao.queryPositionById(position_id);
    }

    @Override
    public Integer countPositionAll() {
        return resumepositionDao.countPositionAll();
    }

    @Override
    public Integer countPositionDelAll() {
        return resumepositionDao.countPositionDelAll();
    }

    @Override
    public Integer countPositionByFive() {
        return resumepositionDao.countPositionByFive();
    }

    @Override
    public List<Resumeposition> selectPositionFive(Resumeposition resumeposition) {
        return resumepositionDao.selectPositionFive(resumeposition);
    }

    @Override
    public Boolean addPositionAll(Resumeposition resumeposition) {
        return resumepositionDao.addPositionAll(resumeposition);
    }

    @Override
    public Boolean addPositionSelective(Resumeposition resumeposition) {
        return resumepositionDao.addPositionSelective(resumeposition);
    }

    @Override
    public Boolean updatePositionAllById(Integer position_id) {
        return resumepositionDao.updatePositionAllById(position_id);
    }

    @Override
    public Boolean updatePositionSelective(Resumeposition resumeposition) {
        return resumepositionDao.updatePositionSelective(resumeposition);
    }

    @Override
    public Boolean deletePosition(Integer position_id) {
        return resumepositionDao.deletePosition(position_id);
    }

    @Override
    public Boolean deleteByIdList(List<Resumeposition> resumepositionlist) {
        return resumepositionDao.deleteByIdList(resumepositionlist);
    }
}
