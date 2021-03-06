package com.wizard.demo01.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wizard.demo01.common.utils.PageUtil;
import com.wizard.demo01.model.entity.SysUserEntity;

import java.util.Map;

/**
 * @author wizard_0992
 * @date 2020/1/3 11:02
 */

public interface SysUserService extends IService<SysUserEntity> {

    //修改密码
    boolean updatePassword(Long userId,String oldPassword,String newPassword);

    PageUtil queryPage(Map<String,Object> map);

    void saveUser(SysUserEntity entity);

    SysUserEntity getInfo(Long userId);

    void updateUser(SysUserEntity entity);

    void deleteUser(Long[] ids);

    void updatePsd(Long[] ids);

}
