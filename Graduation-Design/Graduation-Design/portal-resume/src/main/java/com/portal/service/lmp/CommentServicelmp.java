package com.portal.service.lmp;

import com.portal.mapper.commentDao;
import com.portal.pojo.Comment;
import com.portal.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Service
public class CommentServicelmp implements CommentService {

    @Autowired
    private commentDao commentdao;

    @Override
    public List<Comment> showCommentByPosition(Integer pageNum, Integer pageSize) {
        return commentdao.showCommentByPosition(pageNum,pageSize);
    }

    @Override
    public List<Comment> showCommentByUsers(Integer pageNum, Integer pageSize) {
        return commentdao.showCommentByUsers(pageNum,pageSize);
    }

    @Override
    public List<Comment> outsideURL(Integer pageNum, Integer pageSize) {
        return commentdao.outsideURL(pageNum,pageSize);
    }

    @Override
    public Integer countcommentAll() {
        return commentdao.countcommentAll();
    }

    @Override
    public Integer countcommentQuarter() {
        return commentdao.countcommentQuarter();
    }

    @Override
    public Integer countcommentYear() {
        return commentdao.countcommentYear();
    }

    @Override
    public Integer countcommentThisWeek() {
        return commentdao.countcommentThisWeek();
    }
}
