package com.wizard.demo01.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wizard.demo01.common.utils.PageUtil;
import com.wizard.demo01.model.entity.SysRoleEntity;

import java.util.Map;

/**
 * Created by Administrator on 2019/8/3.
 */
public interface SysRoleService extends IService<SysRoleEntity> {


    PageUtil queryPage(Map<String, Object> map);

    void saveRole(SysRoleEntity role);

    void updateRole(SysRoleEntity role);

    void deleteBatch(Long[] ids);
}
