package com.smallren.service;

import com.smallren.entity.SysUser;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: renbaojia
 * @CreateDate: 2019/9/29 15:24
 * @UpdateUser:
 * @UpdateDate: 2019/9/29 15:24
 * @UpdateRemark:
 * @Version: 3.7.1
 */
public interface IUserService {
    List<SysUser> list();

    int save(SysUser user);
}
