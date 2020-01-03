package com.wizard.demo01.server.config;

import com.wizard.demo01.server.shiro.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * ShiroConfig的通用配置
 * @author wizard_0992
 * @date 2019/12/10 11:14
 */
/**
 * 1 配置shiro安全管理器，向安全管理器中注入Realm域
 * 2 配置Realm域：注入密码比较器
 * 3 配置密码比较器
 * 4 配置拦截路径和放行路径
 */

@Configuration
public class ShiroConfig {
    /**
     *安全器管理-管理所有的subject
     * 1、配置安全管理器，并且注入Realm域
     */
    @Bean
    public SecurityManager securityManager(UserRealm userRealm){
        //DefaultWebSecurityManager
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }
    //过滤链配置
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        //设定用户没有登陆认证时的跳转链接、没有授权时的跳转链接；
        shiroFilter.setLoginUrl("login.html");
        shiroFilter.setUnauthorizedUrl("/");
        //过滤器链接配置
        Map<String , String> filterMap = new LinkedHashMap();
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/statics/**", "anon");
        filterMap.put("/login.html", "anon");
        filterMap.put("/sys/login", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/captcha.jpg", "anon");
        filterMap.put("/**","authc");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }
    //关于shiro的Bean的生命周期的管理
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor author = new AuthorizationAttributeSourceAdvisor();
        author.setSecurityManager(securityManager);
        return author;
    }
}