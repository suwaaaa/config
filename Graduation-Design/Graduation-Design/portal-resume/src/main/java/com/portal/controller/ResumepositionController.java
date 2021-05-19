package com.portal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.pojo.Resumeposition;
import com.portal.service.ResumepositionService;
import com.portal.utils.JsonData;
import com.portal.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class ResumepositionController {

    @Autowired
    private ResumepositionService resumepositionService;

    @RequestMapping(value = "/resume/queryPositionAll",method = RequestMethod.GET)
    public List<Resumeposition> queryPositionAll(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Resumeposition> mylist = resumepositionService.queryPositionAll();
        PageInfo<Resumeposition> pageInfo = new PageInfo<>(mylist);
        return pageInfo.getList();
    }

//    public static <T> List<T> mergeListsTwo(boolean removeDuplicate, List<T>... lists) {
//        Class clazz = lists[0].getClass();
//        List<T> list = null;
//        try {
//            list = (List<T>) clazz.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (removeDuplicate) { // 移除重复数据
//            for (int i = 0, len = lists.length; i < len; i++) {
//                Iterator it = lists[i].iterator(); // 迭代器
//                while (it.hasNext()) {
//                    T obj = (T) it.next();
//                    if (!list.contains(obj)) {
//                        list.add(obj);
//                    }
//                }
//            }
//        } else { // 不移除重复数据
//            for (int i = 0, len = lists.length; i < len; i++) {
//                list.addAll(lists[i]);
//            }
//        }
//        return list;
//    }


    @RequestMapping(value = "/resume/queryPositionAllDistinctName",method = RequestMethod.GET)
    public List<Resumeposition> queryPositionAllDistinctName(){
        List<Resumeposition> mylist = resumepositionService.queryPositionAll();
        Set<String> set = new HashSet<>();
        List<Resumeposition> newList = new ArrayList<>();
        for (Resumeposition str :mylist) {
            if(set.add(str.getPosition_name())){ //重复的话返回false
                newList.add(str);
            }
        }
        return newList;
    }



    @RequestMapping(value = "/resume/queryPositionAllDistinctCompany",method = RequestMethod.GET)
    public List<Resumeposition> queryPositionAllDistinctCompany(){
        List<Resumeposition> mylist = resumepositionService.queryPositionAll();
//        Set<String> set = new HashSet<>();
        List<Resumeposition> newList = new ArrayList<>();
        List<Resumeposition> distinctClass = mylist.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator.comparing(o ->
                                 o.getPosition_company()))), ArrayList::new));
//        for (Resumeposition str :mylist) {
//            if(set.add(str.getPosition_id().toString())
//                    &&set.add(str.getPosition_name())
//                    &&set.add(str.getPosition_company())
//                    &&set.add(str.getTechnical_requirement())
//                    &&set.add(str.getWork_salary()))
//            { //重复的话返回false
//                newList.add(str);
//            }
//        }
        return distinctClass;
    }

    @RequestMapping(value = "/resume/queryPositionAllDistinctRequirement",method = RequestMethod.GET)
    public List<Resumeposition> queryPositionAllDistinctRequirement(){
        List<Resumeposition> mylist = resumepositionService.queryPositionAll();
        List<Resumeposition> distinctClass = mylist.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator.comparing(o ->
                                o.getTechnical_requirement()))), ArrayList::new));
        return distinctClass;
    }


    @RequestMapping(value = "/resume/queryPositionAllDistinctSalary",method = RequestMethod.GET)
    public List<Resumeposition> queryPositionAllDistinctSalary(){
        List<Resumeposition> mylist = resumepositionService.queryPositionAll();
        List<Resumeposition> distinctClass = mylist.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator.comparing(o ->
                                o.getWork_salary()))), ArrayList::new));
        return distinctClass;
    }

    @RequestMapping(value = "/resume/queryPositionAllDistinctType",method = RequestMethod.GET)
    public List<Resumeposition> queryPositionAllDistinctType(){
        List<Resumeposition> mylist = resumepositionService.queryPositionAll();
        List<Resumeposition> distinctClass = mylist.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator.comparing(o ->
                                o.getPosition_type()))), ArrayList::new));
        return distinctClass;
    }

    @RequestMapping(value = "/resume/queryPositionDelAll",method = RequestMethod.GET)
    public List<Resumeposition> queryPositionDelAll(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Resumeposition> mylist = resumepositionService.queryPositionDelAll();
        PageInfo<Resumeposition> pageInfo = new PageInfo<>(mylist);
        return pageInfo.getList();
    }



    @RequestMapping(value = "/resume/queryPositionPending",method = RequestMethod.GET)
    public List<Resumeposition> queryPositionPending(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Resumeposition> mylist = resumepositionService.queryPositionPending();
        PageInfo<Resumeposition> pageInfo = new PageInfo<>(mylist);
        return pageInfo.getList();
    }



    @RequestMapping(value = "/resume/queryPositionUrgent",method = RequestMethod.GET)
    public List<Resumeposition> queryPositionUrgent(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Resumeposition> mylist = resumepositionService.queryPositionUrgent();
        PageInfo<Resumeposition> pageInfo = new PageInfo<>(mylist);
        return pageInfo.getList();
    }



    @RequestMapping(value = "/resume/queryPositionById/{position_id}",method = RequestMethod.GET)
    public List<Resumeposition> queryPositionById(Integer position_id){
        return resumepositionService.queryPositionById(position_id);
    }



    @RequestMapping(value = "/resume/countPositionAll",method = RequestMethod.GET)
    public Integer countPositionAll(){
        return resumepositionService.countPositionAll();
    }



    @RequestMapping(value = "/resume/countPositionDelAll",method = RequestMethod.GET)
    public Integer countPositionDelAll(){
        return resumepositionService.countPositionDelAll();
    }



    @RequestMapping(value = "/resume/countPositionByFive",method = RequestMethod.GET)
    public Integer countPositionByFive(){
        return resumepositionService.countPositionByFive();
    }



    @RequestMapping(value = "/resume/selectPositionFive",method = RequestMethod.GET)
    public List<Resumeposition> selectPositionFive(Resumeposition resumeposition) {
        return resumepositionService.selectPositionFive(resumeposition);
    }



    @RequestMapping(value = "/resume/addPositionAll",method = RequestMethod.PUT)
    public Object addPositionAll(Resumeposition resumeposition){
        Boolean i = resumepositionService.addPositionAll(resumeposition);
        if (i ==true ){
        return JsonData.buildSuccess();
        }
        return JsonData.buildError("addPositionAll fail");
    }



    @RequestMapping(value = "/resume/addPositionSelective",method = RequestMethod.POST)
    public Object addPositionSelective(Resumeposition resumeposition){
        Boolean i = resumepositionService.addPositionSelective(resumeposition);
        if (i ==true ){
            return JsonData.buildSuccess();
        }
        return JsonData.buildError("addPositionSelective fail");
    }



    @RequestMapping(value = "/resume/updatePositionAllById/{position_id]",method = RequestMethod.PUT)
    public Object updatePositionAllById(Integer position_id){
        Boolean i = resumepositionService.updatePositionAllById(position_id);
        if (i ==true ){
            return JsonData.buildSuccess();
        }
        return JsonData.buildError("updatePositionAllById fail");
    }


    @RequestMapping(value = "/resume/updatePositionSelective",method = RequestMethod.POST)
    public Object updatePositionSelective(Resumeposition resumeposition){
        Boolean i = resumepositionService.updatePositionSelective(resumeposition);
        if (i ==true ){
            return JsonData.buildSuccess();
        }
        return JsonData.buildError("updatePositionSelective fail");
    }



    @RequestMapping(value = "/resume/deletePosition/{position_id}",method = RequestMethod.DELETE)
    public Object deletePosition(Integer position_id){
        Boolean i = resumepositionService.deletePosition(position_id);
        if (i ==true ){
            return JsonData.buildSuccess();
        }
        return JsonData.buildError("deletePosition fail");
    }



    @RequestMapping(value = "/resume/deleteByIdList",method = RequestMethod.DELETE)
    public Object deleteByIdList(List<Resumeposition> resumepositionlist){
        Boolean i = resumepositionService.deleteByIdList(resumepositionlist);
        if (i ==true ){
            return JsonData.buildSuccess();
        }
        return JsonData.buildError("deleteByIdList fail");
    }


    //Redis     实现    热度搜索   ，根据缓存
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    //每次点击给相关词searchkey热度 +1
    public int incrementScore(String searchkey) {
        String key = searchkey;
        Long now = System.currentTimeMillis();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        zSetOperations.incrementScore("position_name", key, 1);
        valueOperations.getAndSet(key, String.valueOf(now));
        return 1;
    }

    @RequestMapping(value = "/incrementScoreByUser" ,method = RequestMethod.POST)
    @ResponseBody
    public int incrementScoreByUserId(String searchkey) {
        Long now = System.currentTimeMillis();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        List<String> title = new ArrayList<>();
        title.add(searchkey);
        for (int i = 0, lengh = title.size(); i < lengh; i++) {
            String tle = title.get(i);
            try {
                if (zSetOperations.score("position_name", tle) <= 0) {
                    zSetOperations.add("position_name", tle, 0);
                    valueOperations.set(tle, String.valueOf(now));
                }
            } catch (Exception e) {
                zSetOperations.add("position_name", tle, 0);
                valueOperations.set(tle, String.valueOf(now));
            }
        }
        return 1;
    }

    @RequestMapping(value = "/resume/hotsearch",method = RequestMethod.POST)
    public List<String> hotSearch(@RequestParam String searchkey){

        int i = incrementScore(searchkey);
        System.out.println("每次点击给相关词searchkey热度---"+searchkey+"---"+i);

        String key = searchkey;
        Long now = System.currentTimeMillis();
        List<String> result = new ArrayList<>();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Set<String> value = zSetOperations.reverseRangeByScore("position_name", 0, Double.MAX_VALUE);
        //key不为空的时候 推荐相关的最热前十名
        if(StringUtils.isNotEmpty(searchkey)){
            for (String val : value) {
                if (StringUtils.containsIgnoreCase(val, key)) {
                    if (result.size() > 9) {//只返回最热的前十名
                        break;
                    }
                    Long time = Long.valueOf(valueOperations.get(val));
                    if ((now - time) < 2592000000L) {//返回最近一个月的数据
                        result.add(val);
                    } else {//时间超过一个月没搜索就把这个词热度归0
                        zSetOperations.add("position_name", val, 0);
                    }
                }
            }
        }else {
            for (String val : value) {
                if (result.size() > 9) {//只返回最热的前十名
                    break;
                }
                Long time = Long.valueOf(valueOperations.get(val));
                if ((now - time) < 2592000000L) {//返回最近一个月的数据
                    result.add(val);
                } else {//时间超过一个月没搜索就把这个词热度归0
                    zSetOperations.add("position_name", val, 0);
                }
            }

        }
        System.out.println(result);
        return result;
    }
}
