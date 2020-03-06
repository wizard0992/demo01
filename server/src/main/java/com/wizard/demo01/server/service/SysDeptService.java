package com.wizard.demo01.server.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.wizard.demo01.model.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/2.
 */
public interface SysDeptService extends IService<SysDeptEntity> {

    List<SysDeptEntity> queryAll(Map<String, Object> map);

    List<Long> queryDeptIds(Long parentId);

    List<Long> getSubDeptIdList(Long deptId);
}
