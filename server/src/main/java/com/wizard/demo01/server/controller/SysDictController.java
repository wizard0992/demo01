package com.wizard.demo01.server.controller;

import com.google.common.collect.Maps;
import com.wizard.demo01.common.response.BaseResponse;
import com.wizard.demo01.common.response.StatusCode;
import com.wizard.demo01.common.utils.PageUtil;
import com.wizard.demo01.common.utils.ValidatorUtil;
import com.wizard.demo01.model.entity.SysDictEntity;
import com.wizard.demo01.server.service.SysDictService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 数据字典
 * @author wizard_0992
 * @date 2019/12/16 9:51
 */
@RestController
@RequestMapping("sys/dict")
public class SysDictController extends AbstractController{

    @Autowired
    private SysDictService sysDictService;

    //列表
    @RequestMapping("/list")
    @RequiresPermissions("sys:dict:list")
    public BaseResponse list(@RequestParam Map<String, Object> params){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            PageUtil page = sysDictService.queryPage(params);

            Map<String,Object> resMap= Maps.newHashMap();
            resMap.put("page", page);

            response.setData(resMap);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


    //详情
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:dict:info")
    public BaseResponse info(@PathVariable Long id){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap=Maps.newHashMap();
        try {
            SysDictEntity entity = sysDictService.getById(id);

            resMap.put("dict", entity);
            response.setData(resMap);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.UpdatePasswordFail);
        }
        return response;
    }

    //新增
    @RequestMapping("/save")
    @RequiresPermissions("sys:dict:save")
    public BaseResponse save(@RequestBody @Validated SysDictEntity dict, BindingResult result){
        String res= ValidatorUtil.checkResult(result);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.Fail.getCode(),res);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            sysDictService.save(dict);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    //修改
    @RequestMapping("/update")
    @RequiresPermissions("sys:dict:update")
    public BaseResponse update(@RequestBody @Validated SysDictEntity dict, BindingResult result){
        String res= ValidatorUtil.checkResult(result);
        if (StringUtils.isNotBlank(res)){
            return new BaseResponse(StatusCode.Fail.getCode(),res);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            sysDictService.updateById(dict);

        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

    //删除
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dict:delete")
    public BaseResponse delete(@RequestBody Long[] ids){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            sysDictService.removeByIds(Arrays.asList(ids));
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }

}