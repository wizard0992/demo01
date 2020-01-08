package com.wizard.demo01.server.controller;

import com.wizard.demo01.server.annotation.LogAnnotation;
import com.google.common.collect.Maps;
import com.wizard.demo01.common.response.BaseResponse;
import com.wizard.demo01.common.response.StatusCode;
import com.wizard.demo01.model.entity.SysUserEntity;
import com.wizard.demo01.server.service.SysUserService;
import com.wizard.demo01.server.shiro.ShiroUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户管理Controller
 * @author wizard_0992
 * @date 2019/12/2 15:19
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取当前登录用户详情
     * @return
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public BaseResponse currInfo(){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        //用来存储user信息
        Map<String,Object> resMap = Maps.newHashMap();
        try{
            resMap.put("user",getUser());
        }catch (Exception e){
            return new BaseResponse(StatusCode.Fail);
        }
        response.setData(resMap);
        return response;
    }

    /**
     * 修改登录密码
     * @param password
     * @param newPassword
     * @return
     */
    @RequestMapping("/password")
    @LogAnnotation("修改登录密码")
    public BaseResponse updatePassword(String password,String newPassword){
        if(StringUtils.isBlank(password) || StringUtils.isBlank(newPassword)){
            return new BaseResponse(StatusCode.PasswordCanNotBlank);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            //真正的处理逻辑：先校验旧密码输入是否正确，再更新
            SysUserEntity entity = getUser();
            final String salt = entity.getSalt();

            String oldPsd = ShiroUtil.sha256(password,salt);
            if(!entity.getPassword().equals(oldPsd)){
                return new BaseResponse(StatusCode.OldPasswordNotMatch);
            }
            String newPsd = ShiroUtil.sha256(newPassword,salt);
            //执行更行代码
            log.info("旧密码正确，开始更新新密码");

            sysUserService.updatePassword(entity.getUserId(),oldPsd,newPsd);
        }catch (Exception e){
            response = new BaseResponse(StatusCode.UpdatePasswordFail);
        }
        return response;
    }

}
