package com.portal.service;

import com.portal.pojo.User;

import java.util.List;

public interface UserService {
    boolean addUser(User user);

    User queryUser(Long id);

    List<User> queryUserAll();

    Boolean addUserSelective(User user);

    Boolean deleteUser(Long id);

    Boolean updateUserSelective(User user);

    Boolean updateUserAll(User user);

    Boolean uploadUserImg(User user);

    Integer queryUserNumAll();
}
