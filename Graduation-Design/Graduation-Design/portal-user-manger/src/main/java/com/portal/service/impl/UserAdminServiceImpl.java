package com.portal.service.impl;

import com.portal.mapper.UserAdminMapper;
import com.portal.pojo.User2;
import com.portal.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserAdminServiceImpl implements UserAdminService {

    @Autowired
    private UserAdminMapper userAdminMapper;


    @Override
    public List<User2> getUserList(User2 user2) {
        return  userAdminMapper.getUserListLike(user2);
    }

    @Override
    public User2 getOneUser(Integer id) {
        User2 condition = new User2();
        condition.setUserId(id);
        return userAdminMapper.selectOne(condition);
    }

    @Override
    public Integer saveUser(User2 user2) {
        return userAdminMapper.insert(user2);
    }

    @Override
    public Integer updateUser(User2 user2) {
        return userAdminMapper.updateByPrimaryKeySelective(user2);
    }

    @Override
    //加上事务处理
    @Transactional
    public Integer deleteUser(Integer id) {
        User2 condition = new User2();
        condition.setUserId(id);
        //查找到该用户
        User2 user2 = userAdminMapper.selectOne(condition);
        user2.setActive(false);
        //更新用户
        return userAdminMapper.updateByPrimaryKeySelective(user2);
    }



    @Override
    public User2 getUserByPhone(String phone) {
        User2 condition = new User2();
        condition.setUserPhone(phone);
        return userAdminMapper.selectOne(condition);
    }

    @Override
    public User2 getUserByEmail(String email) {
        User2 condition = new User2();
        condition.setUserEmail(email);
        return userAdminMapper.selectOne(condition);
    }

    @Override
    public User2 getUserByUsername(String username) {
        User2 condition = new User2();
        if(username.contains(".com")){
            condition.setUserEmail(username);
        }else{
            condition.setUserPhone(username);
        }
        return userAdminMapper.selectOne(condition);
    }

    @Override
    public User2 getUserByOnlyName(String username) {
        User2 condition = new User2();
        condition.setUserName(username);
        return userAdminMapper.selectOne(condition);
    }
}
