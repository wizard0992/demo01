package com.wizard.demo01.model.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
/**
 * 角色与部门关联实体
 * @author wizard_0992
 * @date 2019/12/2 14:23
 */

@Data
@TableName("sys_role_dept")
public class SysRoleDeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId
	private Long id;

	//角色ID
	private Long roleId;

	//部门ID
	private Long deptId;

	
}
