package com.wizard.demo01.server.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.wizard.demo01.common.utils.PageUtil;
import com.wizard.demo01.model.entity.AttendRecordEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/5
 */
public interface AttendRecordService extends IService<AttendRecordEntity> {

    PageUtil queryPage(Map<String, Object> params);

    List<AttendRecordEntity> selectAll(Map<String, Object> params);

    List<Map<Integer, Object>> manageExport(List<AttendRecordEntity> list);
}
