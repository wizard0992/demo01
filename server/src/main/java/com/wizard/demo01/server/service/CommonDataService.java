package com.wizard.demo01.server.service;

import java.util.Set;

/**
 * Created by Administrator on 2019/8/5.
 */
public interface CommonDataService {

    Set<Long> getCurrUserDataDeptIds();

    String getCurrUserDataDeptIdsStr();
}
