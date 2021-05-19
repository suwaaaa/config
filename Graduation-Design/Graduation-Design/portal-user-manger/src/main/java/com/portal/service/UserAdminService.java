package com.portal.service;

import com.portal.pojo.User2;

import java.util.List;

public interface UserAdminService {
    public List<User2> getUserList(User2 user2);
    public User2 getOneUser(Integer id);
    public Integer saveUser(User2 user2);
    public Integer updateUser(User2 user2);
    public Integer deleteUser(Integer id);

    public User2 getUserByPhone(String phone);
    public User2 getUserByEmail(String email);
    public User2 getUserByUsername(String username);
    public User2 getUserByOnlyName(String username);
}
