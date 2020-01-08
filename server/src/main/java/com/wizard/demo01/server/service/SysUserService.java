package com.wizard.demo01.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wizard.demo01.model.entity.SysUserEntity;
import com.wizard.demo01.server.util.PageUtil;

import java.util.Map;

/**
 * @author wizard_0992
 * @date 2020/1/3 11:02
 */

public interface SysUserService extends IService<SysUserEntity> {

    //修改密码
    boolean updatePassword(Long userId,String oldPassword,String newPassword);

}
