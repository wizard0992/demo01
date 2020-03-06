package com.wizard.demo01.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Joiner;
import com.wizard.demo01.common.utils.CommonUtil;
import com.wizard.demo01.model.entity.SysUserRoleEntity;
import com.wizard.demo01.model.mapper.SysUserRoleDao;
import com.wizard.demo01.server.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/8/4 0:11
 **/
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {

    @Override
    public void deleteBatch(List<Long> roleIds) {
        if (roleIds!=null && !roleIds.isEmpty()){
            String delIds= Joiner.on(",").join(roleIds);
            baseMapper.deleteBatch(CommonUtil.concatStrToInt(delIds,","));
        }
    }

    //维护用户~角色的关联关系
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long userId, List<Long> roleIds) {
        //需要先清除旧的关联数据，再插入新的关联信息
        this.remove(new QueryWrapper<SysUserRoleEntity>().eq("user_id",userId));

        if (roleIds!=null && !roleIds.isEmpty()){
            SysUserRoleEntity entity;
            for (Long rId:roleIds){
                entity=new SysUserRoleEntity();
                entity.setRoleId(rId);
                entity.setUserId(userId);
                this.save(entity);
            }
        }
    }

    //获取分配给用户的角色id列表
    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return baseMapper.queryRoleIdList(userId);
    }
}


















