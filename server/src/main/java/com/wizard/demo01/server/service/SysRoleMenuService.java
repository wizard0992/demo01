package com.wizard.demo01.server.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.wizard.demo01.model.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * Created by Administrator on 2019/8/3.
 */
public interface SysRoleMenuService extends IService<SysRoleMenuEntity> {

    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    void deleteBatch(List<Long> roleIds);

    List<Long> queryMenuIdList(Long roleId);
}
