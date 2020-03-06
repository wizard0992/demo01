package com.wizard.demo01.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wizard.demo01.common.utils.PageUtil;
import com.wizard.demo01.model.entity.SysLogEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wizard_0992
 * @date 2019/12/16 10:21
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtil queryPage(Map<String, Object> params);

    void truncate();

}