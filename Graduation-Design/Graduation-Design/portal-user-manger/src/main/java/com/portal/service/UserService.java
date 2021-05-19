package com.portal.service;


import com.portal.pojo.User2;


public interface UserService {
    public User2 getUserByPhone(String phone);
    public User2 getUserByEmail(String email);
    public User2 getUserByUsername(String username);

    public User2 getUserByNickName(String nickName);
    public Integer insertUser(User2 user2);
    public Integer updateUser(User2 user2);
    public User2 getUserByPhoneAndPassword(String phone, String password);
    public User2 getUserByEmailAndPassword(String email, String password);
}
