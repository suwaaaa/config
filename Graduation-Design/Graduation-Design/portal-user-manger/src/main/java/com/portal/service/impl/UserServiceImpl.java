package com.portal.service.impl;


import com.portal.mapper.UserMapper;
import com.portal.pojo.*;
import com.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User2 getUserByPhone(String phone) {
        User2 condition = new User2();
        condition.setUserPhone(phone);
        return userMapper.selectOne(condition);
    }

    @Override
    public User2 getUserByEmail(String email) {
        User2 condition = new User2();
        condition.setUserEmail(email);
        return userMapper.selectOne(condition);
    }

    @Override
    public User2 getUserByUsername(String username) {
        User2 condition = new User2();
        if(username.contains(".com")){
            condition.setUserEmail(username);
        }else{
            condition.setUserPhone(username);
        }
        return userMapper.selectOne(condition);
    }

    @Override
    public User2 getUserByNickName(String nickName) {
        User2 condition = new User2();
        condition.setUserName(nickName);
        return userMapper.selectOne(condition);
    }

    @Override
    public Integer insertUser(User2 user2) {
        return userMapper.insert(user2);
    }

    @Override
    public Integer updateUser(User2 user2) {
        return userMapper.updateByPrimaryKeySelective(user2);
    }

    @Override
    public User2 getUserByPhoneAndPassword(String phone, String password) {
        User2 condition = new User2();
        condition.setUserEmail(phone);

        return null;
    }

    @Override
    public User2 getUserByEmailAndPassword(String email, String password) {
        return null;
    }


}
