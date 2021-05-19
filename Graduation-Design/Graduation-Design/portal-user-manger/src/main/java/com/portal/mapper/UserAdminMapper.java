package com.portal.mapper;

import com.portal.pojo.User2;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserAdminMapper extends Mapper<User2> {
    public List<User2> getUserListLike(User2 user2);
}
