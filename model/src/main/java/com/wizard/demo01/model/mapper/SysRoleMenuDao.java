package com.wizard.demo01.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wizard.demo01.model.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 角色与菜单对应关系
 * @author wizard_0992
 * @date 2019/12/2 14:23
 */

@Mapper
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenuEntity> {
	
	//根据角色Id，获取菜单Id列表
	List<Long> queryMenuIdList(Long roleId);

	//根据角色Id列表，批量删除
	int deleteBatch(@Param("roleIds") String roleIds);
}
