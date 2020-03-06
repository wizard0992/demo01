package com.wizard.demo01.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wizard.demo01.common.utils.PageUtil;
import com.wizard.demo01.model.entity.SysDictEntity;

import java.util.Map;

//数据字典
public interface SysDictService extends IService<SysDictEntity> {

    PageUtil queryPage(Map<String, Object> params);
}

