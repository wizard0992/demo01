package com.wizard.demo01.server.controller;

import com.wizard.demo01.model.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * 日志打印
 * @author wizard_0992
 * @date 2019/12/2 16:08
 */
@Controller
public class AbstractController {

    //日志打印
    protected Logger log= LoggerFactory.getLogger(getClass());

    //获取当前用户详细登录信息
    protected SysUserEntity getUser() {
        SysUserEntity user = (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
        return user;
    }

    protected Long getUserId(){
        return getUser().getUserId();
    }

    protected String getUserName(){
        return getUser().getUsername();
    }

    protected Long getDeptId(){
        return getUser().getDeptId();
    }

}
