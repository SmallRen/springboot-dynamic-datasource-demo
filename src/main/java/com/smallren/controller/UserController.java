package com.smallren.controller;

import com.smallren.entity.User;
import com.smallren.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description: user控制器类
 * @Author: renbaojia
 * @CreateDate: 2019/8/8 10:42
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    /**
     * 从库查询
     *
     * @return List
     */
    @RequestMapping("/select")
    @ResponseBody
    public List<User> select() {
        return userService.select();
    }

    /**
     * 主库插入
     *
     * @param user
     * @return int
     */
    @RequestMapping("/save")
    @ResponseBody
    public int save(User user) {
        return userService.save(user);
    }
}
