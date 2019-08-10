package com.smallren.service;

import com.smallren.entity.User;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: renbaojia
 * @CreateDate: 2019/8/8 10:55
 */
public interface IUserService {
    List<User> select();

    int save(User user);
}
