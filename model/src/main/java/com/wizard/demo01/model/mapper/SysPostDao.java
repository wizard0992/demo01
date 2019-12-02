package com.wizard.demo01.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wizard.demo01.model.entity.SysPostEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * 岗位管理
 * @author wizard_0992
 * @date 2019/12/2 14:23
 */

@Mapper
public interface SysPostDao extends BaseMapper<SysPostEntity> {

    int deleteBatch(@Param("ids") String ids);

}
