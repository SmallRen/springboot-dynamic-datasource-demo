package com.smallren.service.impl;

import com.smallren.entity.SysUser;
import com.smallren.mapper.SysUserMapper;
import com.smallren.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {
    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    @Cacheable(value = "selectUser", key = "targetClass + methodName")
    public List<SysUser> list() {
        return sysUserMapper.selectByExample(null);
    }

    @Override
    @CacheEvict(value = "selectUser", allEntries = true)
    public int save(SysUser user) {
        return sysUserMapper.insert(user);
    }

}
