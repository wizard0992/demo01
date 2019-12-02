package com.wizard.demo01.model.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wizard.demo01.model.entity.SysDeptEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * @author wizard_0992
 * @date 2019/12/2 14:23
 */
//部门管理
@Mapper
public interface SysDeptDao extends BaseMapper<SysDeptEntity> {

    List<SysDeptEntity> queryList(Map<String, Object> params);

    List<SysDeptEntity> queryListV2(Map<String, Object> params);


    //根据父级部门id查询子部门id列表
    List<Long> queryDeptIds(Long parentId);

    Set<Long> queryAllDeptIds();
}
