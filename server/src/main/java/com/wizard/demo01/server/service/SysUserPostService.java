package com.wizard.demo01.server.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.wizard.demo01.model.entity.SysUserPostEntity;

import java.util.List;

/**
 * Created by Administrator on 2019/8/4.
 */
public interface SysUserPostService extends IService<SysUserPostEntity> {

    String getPostNameByUserId(Long userId);

    void saveOrUpdate(Long userId, List<Long> postIds);

    List<Long> queryPostIdList(Long userId);
}
