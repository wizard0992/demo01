package com.wizard.demo01.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wizard.demo01.common.utils.PageUtil;
import com.wizard.demo01.common.utils.QueryUtil;
import com.wizard.demo01.model.entity.SysRoleEntity;
import com.wizard.demo01.model.mapper.SysRoleDao;
import com.wizard.demo01.server.service.SysRoleDeptService;
import com.wizard.demo01.server.service.SysRoleMenuService;
import com.wizard.demo01.server.service.SysRoleService;
import com.wizard.demo01.server.service.SysUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 角色 服务service
 * @Author:debug (SteadyJack)
 * @Date: 2019/8/3 22:37
 **/
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysRoleDeptService sysRoleDeptService;

    @Autowired
    private SysUserRoleService sysUserRoleService;



    private static final Logger log= LoggerFactory.getLogger(SysRoleServiceImpl.class);

    //分页列表模糊查询
    @Override
    public PageUtil queryPage(Map<String, Object> map) {
        String search= (map.get("search")!=null)? (String) map.get("search") : "";

        IPage<SysRoleEntity> iPage=new QueryUtil<SysRoleEntity>().getQueryPage(map);

        QueryWrapper wrapper=new QueryWrapper<SysRoleEntity>()
                .like(StringUtils.isNotBlank(search),"role_name",search);

        IPage<SysRoleEntity> resPage=this.page(iPage,wrapper);
        return new PageUtil(resPage);
    }

    //新增
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(SysRoleEntity role) {
        role.setCreateTime(DateTime.now().toDate());
        this.save(role);

        //插入角色~菜单关联信息
        sysRoleMenuService.saveOrUpdate(role.getRoleId(),role.getMenuIdList());

        //插入角色~部门关联信息
        sysRoleDeptService.saveOrUpdate(role.getRoleId(),role.getDeptIdList());
    }

    //修改
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(SysRoleEntity role) {
        this.updateById(role);

        //更新角色~菜单关联信息
        sysRoleMenuService.saveOrUpdate(role.getRoleId(),role.getMenuIdList());

        //更新角色~部门关联信息
        sysRoleDeptService.saveOrUpdate(role.getRoleId(),role.getDeptIdList());
    }


    //批量删除
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] ids) {
        List<Long> roleIds=Arrays.asList(ids);
        this.removeByIds(roleIds);

        //删除角色~菜单关联数据
        sysRoleMenuService.deleteBatch(roleIds);

        //删除角色~部门关联数据
        sysRoleDeptService.deleteBatch(roleIds);

        //删除角色~用户关联数据
        sysUserRoleService.deleteBatch(roleIds);
    }
}