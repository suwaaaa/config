package com.portal.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.pojo.Comment;
import com.portal.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
//@CrossOrigin
//@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/resume/showCommentByPosition",method = RequestMethod.GET)
    public List<Comment> showCommentByPosition(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Comment> mylist = commentService.showCommentByPosition(pageNum,pageSize);
        PageInfo<Comment> pageInfo = new PageInfo<>(mylist);
        return pageInfo.getList();
    }

    @RequestMapping(value = "/resume/showCommentByUsers",method = RequestMethod.GET)
    public List<Comment> showCommentByUsers(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Comment> mylist = commentService.showCommentByUsers(pageNum,pageSize);
        PageInfo<Comment> pageInfo = new PageInfo<>(mylist);
        return pageInfo.getList();
    }

    @RequestMapping(value = "/resume/outsideURL",method = RequestMethod.GET)
    public List<Comment> outsideURL(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Comment> mylist = commentService.outsideURL(pageNum,pageSize);
        PageInfo<Comment> pageInfo = new PageInfo<>(mylist);
        return pageInfo.getList();
    }



    @RequestMapping(value = "/resume/countcommentAll",method = RequestMethod.GET)
    public Integer countcommentAll(){
        return commentService.countcommentAll();
    }



    @RequestMapping(value = "/resume/countcommentQuarter",method = RequestMethod.GET)
    public Integer countcommentQuarter(){
        return commentService.countcommentQuarter();
    }



    @RequestMapping(value = "/resume/countcommentYear",method = RequestMethod.GET)
    public Integer countcommentYear(){
        return commentService.countcommentYear();
    }



    @RequestMapping(value = "/resume/countcommentThisWeek",method = RequestMethod.GET)
    public Integer countcommentThisWeek(){
        return commentService.countcommentThisWeek();
    }

}
