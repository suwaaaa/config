package com.portal.mapper;


import com.portal.pojo.Comment;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Repository
//@Mapper
public interface commentDao {

    @Select("select comment_id,comment_description,comment_pho_url,position_comment,\n" +
            "comment_outside_url,comment_score,comment_creatime,\n" +
            "technical_position,position_location\n" +
            "from comment LEFT join position on comment.comment_id=position.position_id\n" +
            "ORDER by comment_id desc")
//            "LIMIT #{pageNum}, #{pageSize}
    @Results(value = {
            @Result(column="comment_id", property="comment_id"),
            @Result(column="comment_description", property="comment_description"),
            @Result(column="comment_pho_url" , property="comment_pho_url"),
            @Result(column="comment_score" , property="comment_score"),
            @Result(column="comment_creatime" , property="comment_creatime"),
            @Result(column="technical_position" , property="technical_position"),
            @Result(column="position_location" ,property="position_location"),
            @Result(column="position_comment" , property="position_comment"),
            @Result(column="comment_outside_url", property="comment_outside_url")})
    List<Comment> showCommentByPosition(Integer pageNum,Integer pageSize);





    @Select("select comment_id,comment_description,comment_pho_url,\n" +
            "comment_outside_url,comment_score,position_comment,comment_creatime,\n" +
            "user_real_name,user_photo_url,user_email\n" +
            "from comment left join user on comment.comment_id=user.user_id\n" +
            "ORDER by comment_id desc" )
//            "LIMIT #{pageNum}, #{pageSize}"
    @Results(value = {
            @Result(column="comment_id", property="comment_id"),
            @Result(column="comment_description", property="comment_description"),
            @Result(column="comment_pho_url" , property="comment_pho_url"),
            @Result(column="comment_score" , property="comment_score"),
            @Result(column="comment_creatime" , property="comment_creatime"),
            @Result(column="user_real_name" , property="user_real_name"),
            @Result(column="position_comment" , property="position_comment"),
            @Result(column="comment_outside_url", property="comment_outside_url"),
            @Result(column="user_email", property="user_email"),})
    List<Comment> showCommentByUsers(Integer pageNum,Integer pageSize);






    @Select("select comment_outside_url from comment")
//    LIMIT #{pageNum}, #{pageSize}
    @Results(value = {
            @Result(column="comment_id", property="comment_id"),
            @Result(column="comment_description", property="comment_description"),
            @Result(column="comment_pho_url" , property="comment_pho_url"),
            @Result(column="comment_score" , property="comment_score"),
            @Result(column="comment_creatime" , property="comment_creatime"),
            @Result(column="comment_outside_url", property="comment_outside_url")})
    List<Comment> outsideURL(Integer pageNum,Integer pageSize);







//    查询历史所有消息数据个数
    @Select("select count(position_comment) from comment")
    Integer countcommentAll();


//   查询本季度数据个数
    @Select("select count(position_comment) from `comment` where QUARTER(comment_creatime)=QUARTER(now())")
    Integer countcommentQuarter();



    //   查询本年数据个数
    @Select("select count(position_comment) from `comment` where QUARTER(comment_creatime)=QUARTER(DATE_SUB(now(),interval 1 QUARTER))")
    Integer countcommentYear();


//  ;
    //   查询当前这周的数据个数
    @Select("SELECT count(position_comment) FROM `comment` WHERE YEARWEEK(date_format(comment_creatime,'%Y-%m-%d')) = YEARWEEK(now())")
    Integer countcommentThisWeek();








}



