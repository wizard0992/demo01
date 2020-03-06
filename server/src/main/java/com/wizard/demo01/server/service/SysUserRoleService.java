package com.wizard.demo01.server.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.wizard.demo01.model.entity.SysUserRoleEntity;

import java.util.List;

/**
 * Created by Administrator on 2019/8/4.
 */
public interface SysUserRoleService extends IService<SysUserRoleEntity> {

    void deleteBatch(List<Long> roleIds);

    void saveOrUpdate(Long userId, List<Long> roleIds);

    List<Long> queryRoleIdList(Long userId);
}
