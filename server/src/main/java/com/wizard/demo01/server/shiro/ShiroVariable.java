package com.wizard.demo01.server.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

/**
 * @author wizard_0992
 * @date 2020/1/12 0:05
 */
@Component
public class ShiroVariable {

    /**
     * 判断当前登录用户（主体）是否有 指定的权限
     *
     * @param permission 指定的权限
     * @return
     */
    public Boolean hasPermission(String permission) {
        Subject subject = SecurityUtils.getSubject();
        if (subject!=null && subject.isPermitted(permission)){
            return true;
        }
        return false;

        //return (subject != null && subject.isPermitted(permission)) ? true : false;
    }
}
