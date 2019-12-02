package com.wizard.demo01.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wizard.demo01.model.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * 菜单管理
 * @author wizard_0992
 * @date 2019/12/2 14:23
 */

@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {
	
	//根据父级Id，查询子菜单
	List<SysMenuEntity> queryListParentId(Long parentId);
	
	//获取不包含按钮的菜单列表
	List<SysMenuEntity> queryNotButtonList();

	//获取所有菜单
	List<SysMenuEntity> queryList();
}
