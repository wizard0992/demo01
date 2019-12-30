package com.wizard.demo01.server.controller;

import com.google.code.kaptcha.Producer;
import com.wizard.demo01.common.response.BaseResponse;
import com.wizard.demo01.common.response.StatusCode;
import com.wizard.demo01.server.shiro.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录Controller
 * @author wizard_0992
 * @date 2019/12/2 15:51
 */

@Controller
public class SysLoginController extends AbstractController{

    private Producer producer;


    /**
     * 登录
     * @param username
     * @param password
     * @param captcha
     * @return
     */
    @RequestMapping(value = "/sys/login",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse login(String username,String password,String captcha){

        log.info("用户名：{} 密码：{} 验证码:{}",username,password,captcha);
        //异常处理
        try{
            //提交登录
            Subject subject = SecurityUtils.getSubject();
            if (!subject.isAuthenticated()){
                UsernamePasswordToken token = new UsernamePasswordToken(username,password);
                subject.login(token);
            }
        }catch (UnknownAccountException e){
            return new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }catch (IncorrectCredentialsException e){
            return new BaseResponse(StatusCode.AccountPasswordNotMatch);
        }catch (LockedAccountException e){
            return new BaseResponse(StatusCode.AccountHasBeenLocked);
        }catch (AuthenticationException e){
            return new BaseResponse(StatusCode.AccountValidateFail);
        }
        return new BaseResponse(StatusCode.Success);
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(){
        //销毁当前的shiro的用户session
        ShiroUtil.logout();
        return "redirect:login.html";
    }

}
