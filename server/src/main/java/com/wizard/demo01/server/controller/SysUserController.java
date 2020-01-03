package com.wizard.demo01.server.controller;

import com.google.common.collect.Maps;
import com.wizard.demo01.common.response.BaseResponse;
import com.wizard.demo01.common.response.StatusCode;
import com.wizard.demo01.model.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 用户管理Controller
 * @author wizard_0992
 * @date 2019/12/2 15:19
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
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
            //获取当前登录用户的详情
            SysUserEntity user =(SysUserEntity) SecurityUtils.getSubject().getPrincipal();
            resMap.put("user",user);
        }catch (Exception e){
            return new BaseResponse(StatusCode.Fail);
        }
        response.setData(resMap);
        return response;
    }

}
