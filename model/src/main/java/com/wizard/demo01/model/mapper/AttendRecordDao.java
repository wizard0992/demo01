package com.wizard.demo01.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wizard.demo01.model.entity.AttendRecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author wizard_0992
 * @date 2019/12/2 14:23
 */
@Mapper
public interface AttendRecordDao extends BaseMapper<AttendRecordEntity> {

    List<AttendRecordEntity> queryPage(IPage<AttendRecordEntity> page, @Param("paramMap") Map<String, Object> paramMap);

    List<AttendRecordEntity> selectExportData(Map<String, Object> params);

    List<AttendRecordEntity> queryPageSqlServer(Map<String, Object> params);


    //List<AttendRecordEntity> selectAllMysql(Map<String,Object> params);
}
























