package com.wizard.demo01.server.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wizard.demo01.common.utils.Constant;
import com.wizard.demo01.model.entity.SysMenuEntity;
import com.wizard.demo01.model.entity.SysUserEntity;
import com.wizard.demo01.model.mapper.SysUserDao;
import com.wizard.demo01.server.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * shiro用于认证用户~授权
 * @author wizard_0992
 * @date 2019/12/10 11:40
 */
@Component
public class UserRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysMenuService sysMenuService;
    /**
     * 资源-权限分配-授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录用户（主体）
        SysUserEntity user= (SysUserEntity) principalCollection.getPrimaryPrincipal();
        Long userId=user.getUserId();
        List<String> perms= Lists.newLinkedList();

        //系统超级管理员拥有最高的权限，不需要发出sql的查询，直接拥有所有权限；否则，则需要根据当前用户id去查询权限列表
        if (userId== Constant.SUPER_ADMIN){
            List<SysMenuEntity> list=sysMenuService.list();
            if (list!=null && !list.isEmpty()){
                perms=list.stream().map(SysMenuEntity::getPerms).collect(Collectors.toList());
            }
        }else{
            perms=sysUserDao.queryAllPerms(userId);
        }

        //对于每一个授权编码进行 , 的解析拆分
        Set<String> stringPermissions= Sets.newHashSet();
        if (perms!=null && !perms.isEmpty()){
            for (String p:perms){
                if (StringUtils.isNotBlank(p)){
                    stringPermissions.addAll(Arrays.asList(StringUtils.split(p.trim(),",")));
                }
            }
        }

        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.setStringPermissions(stringPermissions);
        return info;
    }
    /**
     * 用户认证-登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       /*解决方法01
        System.out.println("调用认证方法");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        final String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        log.info("用户名:{},密码:{}",username,password);
        SysUserEntity entity = sysUserDao.selectOne(new QueryWrapper<SysUserEntity>().eq("username",username));
        //账户不存在
        if (entity == null){
            throw new UnknownAccountException("用户不存在");
        }
        //账户已禁用
        if (0 == entity.getStatus()){
            throw new DisabledAccountException("账户已禁用，请联系管理员");
        }*/
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        final String userName=token.getUsername();
        final String password=String.valueOf(token.getPassword());
        log.info("用户名: {} 密码：{}",userName,password);
        SysUserEntity entity=sysUserDao.selectOne(new QueryWrapper<SysUserEntity>().eq("username",userName));
        //SysUserEntity entity=sysUserDao.selectByUserName(userName);//演示sql注入攻击
        //账户不存在
        if (entity==null){
            throw new UnknownAccountException("账户不存在!");
        }
        //账户被禁用
        if (0 == entity.getStatus()){
            throw new DisabledAccountException("账户已被禁用,请联系管理员!");
        }
        //名文密码匹配
        /*if (!entity.getPassword().equals(password)){
            throw new IncorrectCredentialsException("账号密码不匹配");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(entity,password,getName());*/
        //密码比对判断,第三种验证逻辑
        /*String realPassword=ShiroUtil.sha256(password,entity.getSalt());
        if (StringUtils.isBlank(realPassword) || !realPassword.equals(entity.getPassword())){
            throw new IncorrectCredentialsException("账户密码不匹配!");
        }
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(entity,password,getName());*/
        //第一种验证逻辑-交给shiro的密码匹配器去实现
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(entity, entity.getPassword(), ByteSource.Util.bytes(entity.getSalt()), getName());
       return info;
    }
    /**
     * 密码匹配器 逻辑匹配
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher){
        //解密算法
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtil.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtil.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
