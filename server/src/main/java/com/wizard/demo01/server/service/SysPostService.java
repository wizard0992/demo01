package com.wizard.demo01.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wizard.demo01.common.utils.PageUtil;
import com.wizard.demo01.model.entity.SysPostEntity;

import java.util.Map;

/**
 * 岗位模块处理逻辑
 * @author wizard_0992
 * @date 2020/2/6 15:09
 */

public interface SysPostService extends IService<SysPostEntity> {

    PageUtil queryPage(Map<String,Object> params);

    void savePost(SysPostEntity entity);

    void updatePost(SysPostEntity entity);

    void deletePatch(Long[] ids);

}
