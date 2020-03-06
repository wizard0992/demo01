package com.wizard.demo01.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizard.demo01.common.utils.PageUtil;
import com.wizard.demo01.common.utils.QueryUtil;
import com.wizard.demo01.model.entity.SysDictEntity;
import com.wizard.demo01.model.mapper.SysDictDao;
import com.wizard.demo01.server.service.SysDictService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDictEntity> implements SysDictService {

    @Override
    public PageUtil queryPage(Map<String, Object> params) {
        String name = (String)params.get("name");

        IPage queryPage=new QueryUtil<SysDictEntity>().getQueryPage(params);

        //查询包装器
        QueryWrapper<SysDictEntity> wrapper=new QueryWrapper<SysDictEntity>()
                .like(StringUtils.isNotBlank(name),"name", name);
        IPage<SysDictEntity> page=this.page(queryPage,wrapper);

        return new PageUtil(page);
    }

}
