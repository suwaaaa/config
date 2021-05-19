package com.portal.service;


import com.portal.pojo.OnlyComment;
import com.portal.pojo.Resumecomment;

import java.util.List;

public interface ResumeCommentService {

    List<Resumecomment> queryCommentDescriptionAll();

    List<Resumecomment> queryCommentDescriptionByPositionId(Integer position_id);

    List<Resumecomment> queryCommentUserAll();

    List<Resumecomment> queryCommentUserByUserId(Integer position_id);

    List<OnlyComment> showOnlyCommentsByPositionId(Integer position_id);

    Integer countCommentAllNum();

    Integer countcommentQuarter();

    Integer countcommentYear();

    Integer countcommentThisWeek();

    Integer addComment(Resumecomment resumecomment);




    Integer countCommentDescriptionNotNull();

    Integer countUserCommentNotNull();
}
