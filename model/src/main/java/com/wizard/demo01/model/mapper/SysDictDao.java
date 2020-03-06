package com.wizard.demo01.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wizard.demo01.model.entity.SysDictEntity;
import org.apache.ibatis.annotations.Mapper;
/**
 * 数据字典
 * @author wizard_0992
 * @date 2019/12/2 14:23
 */

@Mapper
public interface SysDictDao extends BaseMapper<SysDictEntity> {
	
}
