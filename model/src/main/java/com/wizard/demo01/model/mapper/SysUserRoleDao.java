package com.wizard.demo01.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wizard.demo01.model.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 角色与用户关联关系
 * @author wizard_0992
 * @date 2019/12/2 14:23
 */

@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRoleEntity> {
	
	//根据用户Id 获取角色Id列表
	List<Long> queryRoleIdList(Long userId);

	//根据角色Id列表，批量删除
	int deleteBatch(@Param("roleIds") String roleIds);
}
