package com.wizard.demo01.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Joiner;
import com.wizard.demo01.common.utils.CommonUtil;
import com.wizard.demo01.model.entity.SysRoleMenuEntity;
import com.wizard.demo01.model.mapper.SysRoleMenuDao;
import com.wizard.demo01.server.service.SysRoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 角色菜单服务
 * @Author:debug (SteadyJack)
 * @Date: 2019/8/3 16:22
 **/
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

    private static final Logger log= LoggerFactory.getLogger(SysRoleMenuServiceImpl.class);

    //维护角色~菜单关联信息
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        //需要先清除旧的关联数据，再插入新的关联信息
        deleteBatch(Arrays.asList(roleId));

        SysRoleMenuEntity entity;
        if (menuIdList!=null && !menuIdList.isEmpty()){
            for (Long mId:menuIdList){
                entity=new SysRoleMenuEntity();
                entity.setRoleId(roleId);
                entity.setMenuId(mId);
                this.save(entity);
            }
        }

    }


    //根据角色id批量删除
    @Override
    public void deleteBatch(List<Long> roleIds) {
        if (roleIds!=null && !roleIds.isEmpty()){
            String delIds= Joiner.on(",").join(roleIds);
            baseMapper.deleteBatch(CommonUtil.concatStrToInt(delIds,","));
        }
    }

    //获取角色对应的菜单列表
    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return baseMapper.queryMenuIdList(roleId);
    }

}