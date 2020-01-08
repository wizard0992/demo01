package com.wizard.demo01.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizard.demo01.model.entity.SysUserEntity;
import com.wizard.demo01.model.mapper.SysUserDao;
import com.wizard.demo01.server.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @author wizard_0992
 * @date 2020/1/4 16:35
 */

@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao,SysUserEntity> implements SysUserService{

    //更新密码，借助于mybatispuls的方法;
    @Override
    public boolean updatePassword(Long userId, String oldPassword, String newPassword) {
        SysUserEntity entity = new SysUserEntity();
        entity.setPassword(newPassword);
        return this.update(entity,new QueryWrapper<SysUserEntity>().eq("user_id",userId).eq("password",oldPassword));
    }
}
