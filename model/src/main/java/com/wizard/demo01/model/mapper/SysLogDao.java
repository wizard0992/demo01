package com.wizard.demo01.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wizard.demo01.model.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;
/**
 * 系统日志
 * @author wizard_0992
 * @date 2019/12/2 14:23
 */

@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {

    void truncate();

}
