package com.portal.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.pojo.OnlyComment;
import com.portal.pojo.Resumecomment;
import com.portal.service.ResumeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
public class ResumeCommentController {

    @Autowired
    private ResumeCommentService resumeCommentService;

    @RequestMapping(value = "resume/queryCommentDescriptionAll",method = RequestMethod.GET)
    public List<Resumecomment> queryCommentDescriptionAll(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Resumecomment> mylist = resumeCommentService.queryCommentDescriptionAll();
        List<Resumecomment> newlist = new ArrayList<>();
        for (Resumecomment re: mylist
             ) {
            if(!re.getComment_description().isEmpty()){
                newlist.add(re);
            }
        }
        PageInfo<Resumecomment> pageInfo = new PageInfo<>(newlist);
        return pageInfo.getList();
    }

    @RequestMapping(value = "resume/queryCommentDescriptionByPositionId",method = RequestMethod.GET)
    public List<Resumecomment> queryCommentDescriptionByPositionId(Integer position_id) {
        List<Resumecomment> all = resumeCommentService.queryCommentDescriptionByPositionId(position_id);
        Map<Integer, Resumecomment> map = new HashMap<>();
        List<Resumecomment> result = new ArrayList<>();
        for (Resumecomment c : all) {
            if (c.parent_id == null) {
                result.add(c);
            }
            map.put(c.comment_id, c);
        }
        for (Resumecomment c : all) {
            if (c.parent_id != null) {
                Resumecomment parent = map.get(c.parent_id);
                if (parent.child == null) {
                    parent.child = new ArrayList<>();
                }
                parent.child.add(c);
            }
        }
        return result;
    }

    @RequestMapping(value = "resume/showOnlyCommentsByPositionId",method = RequestMethod.GET)
    public List<OnlyComment> showOnlyCommentsByPositionId(Integer position_id) {
        List<OnlyComment> all = resumeCommentService.showOnlyCommentsByPositionId(position_id);
        Map<Integer, OnlyComment> map = new HashMap<>();
        List<OnlyComment> result = new ArrayList<>();
        for (OnlyComment c : all) {
            if (c.parent_id == null) {
                result.add(c);
            }
            map.put(c.comment_id, c);
        }
        for (OnlyComment c : all) {
            if (c.parent_id != null) {
                OnlyComment parent = map.get(c.parent_id);
                if (parent.child == null) {
                    parent.child = new ArrayList<>();
                }
                parent.child.add(c);
            }
        }
        return result;
    }


    @RequestMapping(value = "resume/queryCommentUserAll",method = RequestMethod.GET)
    public List<Resumecomment> queryCommentUserAll(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Resumecomment> mylist = resumeCommentService.queryCommentUserAll();
        List<Resumecomment> newlist = new ArrayList<>();
        for (Resumecomment re: mylist
        ) {
            if(!re.getUser_comment().isEmpty()){
                newlist.add(re);
            }
        }
        PageInfo<Resumecomment> pageInfo = new PageInfo<>(newlist);
        return pageInfo.getList();
    }

    @RequestMapping(value = "resume/queryCommentUserByUserId",method = RequestMethod.GET)
    public List<Resumecomment> queryCommentUserByUserId(Integer position_id) {
        return resumeCommentService.queryCommentUserByUserId(position_id);
    }

    @RequestMapping(value = "resume/countCommentAllNum",method = RequestMethod.GET)
    public Integer countCommentAllNum() {
        return resumeCommentService.countCommentAllNum();
    }

    @RequestMapping(value = "resume/countcommentQuarter",method = RequestMethod.GET)
    public Integer countcommentQuarter() {
        return resumeCommentService.countcommentQuarter();
    }

    @RequestMapping(value = "resume/countcommentYear",method = RequestMethod.GET)
    public Integer countcommentYear() {
        return resumeCommentService.countcommentYear();
    }

    @RequestMapping(value = "resume/countcommentThisWeek",method = RequestMethod.GET)
    public Integer countcommentThisWeek() {
        return resumeCommentService.countcommentThisWeek();
    }

    @RequestMapping(value = "resume/addComment",method = RequestMethod.POST)
    public Integer addComment(@RequestBody  Resumecomment resumecomment) {
        resumecomment.setComment_creatime(new Date());
        return resumeCommentService.addComment((resumecomment));
    }


    @RequestMapping(value = "resume/countCommentDescriptionNotNull",method = RequestMethod.GET)
    public Integer countCommentDescriptionNotNull() {
        return resumeCommentService.countCommentDescriptionNotNull();
    }

    @RequestMapping(value = "resume/countUserCommentNotNull",method = RequestMethod.GET)
    public Integer countUserCommentNotNull() {
        return resumeCommentService.countUserCommentNotNull();
    }
}
