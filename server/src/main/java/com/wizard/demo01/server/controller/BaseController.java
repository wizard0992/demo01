package com.wizard.demo01.server.controller;

import com.wizard.demo01.common.response.BaseResponse;
import com.wizard.demo01.common.response.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wizard_0992
 * @date 2019/12/2 9:53
 */

@Controller
@RequestMapping("/base")
public class BaseController {
    //打印日志
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);
    /**
     * 测试案例01（只传输一个json格式的数据，不请求界面）
     * @param name
     * @author wizard
     * @date 2019/12/2 10:03
     * 后端以json格式的方式返回到前端，数据的解析和渲染是在前端的
     */

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse info(String name){
        BaseResponse response=new BaseResponse(StatusCode.Success);

        if (StringUtils.isBlank(name)){
            name="权限管理!";
        }
        response.setData(name);
        return response;
    }

    /**
     * 测试案例02（页面跳转-塞值:name）
     * @param name
     * @return
     * @author wizard
     * @date 2019/12/2 10:03
     * 后端以json格式的方式返回到前端，数据的解析和渲染是在前端的
        */

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public String page(String name , ModelMap modelMap){
            if (StringUtils.isBlank(name)){
            name="权限管理!";
            }
            //给前端两个数据
            modelMap.put("name",name);
            modelMap.put("app","权限管理~");

            return "pageOne";
    }
}
