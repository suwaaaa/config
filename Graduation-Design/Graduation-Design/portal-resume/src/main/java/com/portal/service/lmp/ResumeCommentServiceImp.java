package com.portal.service.lmp;

import com.portal.mapper.ResumeCommentDao;
import com.portal.pojo.OnlyComment;
import com.portal.pojo.Resumecomment;
import com.portal.service.ResumeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeCommentServiceImp implements ResumeCommentService {

    @Autowired
    private ResumeCommentDao resumeCommentDao;

    @Override
    public List<Resumecomment> queryCommentDescriptionAll() {
        return resumeCommentDao.queryCommentDescriptionAll();
    }

    @Override
    public List<Resumecomment> queryCommentDescriptionByPositionId(Integer position_id) {
        return resumeCommentDao.queryCommentDescriptionByPositionId(position_id);
    }

    @Override
    public List<Resumecomment> queryCommentUserAll() {
        return resumeCommentDao.queryCommentUserAll();
    }

    @Override
    public List<Resumecomment> queryCommentUserByUserId(Integer position_id) {
        return resumeCommentDao.queryCommentUserByUserId(position_id);
    }

    @Override
    public List<OnlyComment> showOnlyCommentsByPositionId(Integer position_id) {
        return resumeCommentDao.showOnlyCommentsByPositionId(position_id);
    }

    @Override
    public Integer countCommentAllNum() {
        return resumeCommentDao.countCommentAllNum();
    }

    @Override
    public Integer countcommentQuarter() {
        return resumeCommentDao.countcommentQuarter();
    }

    @Override
    public Integer countcommentYear() {
        return resumeCommentDao.countcommentYear();
    }

    @Override
    public Integer countcommentThisWeek() {
        return resumeCommentDao.countcommentThisWeek();
    }

    @Override
    public Integer addComment(Resumecomment resumecomment) {
        return resumeCommentDao.addComment((resumecomment));
    }

    @Override
    public Integer countCommentDescriptionNotNull() {
        return resumeCommentDao.countCommentDescriptionNotNull();
    }

    @Override
    public Integer countUserCommentNotNull() {
        return resumeCommentDao.countUserCommentNotNull();
    }
}
