package com.wizard.demo01.server.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wizard.demo01.model.entity.SysUserEntity;
import com.wizard.demo01.model.mapper.SysUserDao;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    /**
     * 资源-权限分配-授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {


        return null;
    }

    /**
     * 用户认证-登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        final String userName = token.getUsername();
        final String password = String.valueOf(token.getPassword());

        log.info("用户名:{},密码:{}",userName,password);

        SysUserEntity entity = sysUserDao.selectOne(new QueryWrapper<SysUserEntity>().eq("username",userName));

        //账户不存在
        if (entity == null){
            throw new UnknownAccountException("用户不存在");
        }

        //账户已禁用
        if (0 == entity.getStatus()){
            throw new DisabledAccountException("账户已禁用，请联系管理员");
        }
/*

        if (!entity.getPassword().equals(password)){
            throw new IncorrectCredentialsException("账户密码不匹配");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(entity,password,getName());
*/

        //返回信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(entity,password, ByteSource.Util.bytes(entity.getSalt()),getName());

        return info;
    }


    /**
     * 密码匹配器
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher){

        //解密算法
        HashedCredentialsMatcher shaCredentailsMatcher = new HashedCredentialsMatcher();
        shaCredentailsMatcher.setHashAlgorithmName(ShiroUtil.hashAlgorithmName);
        shaCredentailsMatcher.setHashIterations(ShiroUtil.hashIterations);

        super.setCredentialsMatcher(credentialsMatcher);
    }
}
