package com.wizard.demo01.server.service.impl;/**
 * Created by Administrator on 2019/8/5.
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizard.demo01.common.utils.PageUtil;
import com.wizard.demo01.common.utils.QueryUtil;
import com.wizard.demo01.model.entity.SysLogEntity;
import com.wizard.demo01.model.mapper.SysLogDao;
import com.wizard.demo01.server.service.SysLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/8/5 18:00
 **/
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

    @Override
    public PageUtil queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        IPage queryPage=new QueryUtil<SysLogEntity>().getQueryPage(params);

        QueryWrapper<SysLogEntity> wrapper=new QueryWrapper<SysLogEntity>()
                .like(StringUtils.isNotBlank(key),"username", key)
                .or(StringUtils.isNotBlank(key))
                .like(StringUtils.isNotBlank(key),"operation", key);
        IPage<SysLogEntity> page=this.page(queryPage,wrapper);

        return new PageUtil(page);
    }

    @Override
    public void truncate() {
        baseMapper.truncate();
    }

}