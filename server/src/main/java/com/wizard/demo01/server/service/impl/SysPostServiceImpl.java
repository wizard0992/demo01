package com.wizard.demo01.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizard.demo01.common.utils.PageUtil;
import com.wizard.demo01.common.utils.QueryUtil;
import com.wizard.demo01.model.entity.SysPostEntity;
import com.wizard.demo01.model.mapper.SysPostDao;
import com.wizard.demo01.server.service.SysPostService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 岗位模块实现类
 * @author wizard_0992
 * @date 2020/2/6 15:12
 */

@Service("sysPostService")
public class SysPostServiceImpl extends ServiceImpl<SysPostDao, SysPostEntity> implements SysPostService {

    private static final Logger log = LoggerFactory.getLogger(SysPostServiceImpl.class);
    //分页模糊查询
    @Override
    public PageUtil queryPage(Map<String, Object> params) {

        String search = (String) params.get("search");
        //调用自封装的封页查询工具
        IPage<SysPostEntity> queryPage = new QueryUtil<SysPostEntity>().getPage(params);
        QueryWrapper wrapper = new QueryWrapper<SysPostEntity>()
                .like(StringUtils.isNotBlank(search),"post_code",search)
                .or(StringUtils.isNotBlank(search))
                .like(StringUtils.isNotBlank(search),"post_name",search);
        IPage<SysPostEntity> resPage = this.page(queryPage,wrapper);

        return new PageUtil(resPage);

    }
}
