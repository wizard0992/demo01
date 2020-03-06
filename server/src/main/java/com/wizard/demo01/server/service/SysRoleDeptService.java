package com.wizard.demo01.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wizard.demo01.model.entity.SysRoleDeptEntity;

import java.util.List;

/**
 * Created by Administrator on 2019/8/3.
 */
public interface SysRoleDeptService extends IService<SysRoleDeptEntity> {


    void saveOrUpdate(Long roleId, List<Long> deptIdList);

    void deleteBatch(List<Long> roleIds);

    List<Long> queryDeptIdList(Long roleId);
}
