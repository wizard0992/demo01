package com.wizard.demo01.server.controller;

import com.google.common.collect.Maps;
import com.wizard.demo01.common.response.BaseResponse;
import com.wizard.demo01.common.response.StatusCode;
import com.wizard.demo01.server.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 岗位管理
 * @author wizard_0992
 * @date 2019/12/16 9:53
 */

@RestController
@RequestMapping("/sys/post")
public class SysPostController extends AbstractController {

    @Autowired
    private SysPostService sysPostService;

    //列表分页模糊查询
    @RequestMapping("/list")
    public BaseResponse list(@RequestParam Map<String,Object> paramMap){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap = Maps.newHashMap();
        try {

            sysPostService.queryPage(paramMap);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail);
        }
        response.setData(resMap);
        return response;

    }

}
