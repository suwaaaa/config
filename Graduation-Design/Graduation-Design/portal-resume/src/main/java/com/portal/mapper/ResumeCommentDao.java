package com.portal.mapper;

import com.portal.pojo.OnlyComment;
import com.portal.pojo.Resumecomment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ResumeCommentDao {

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
