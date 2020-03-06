package com.wizard.demo01.server.controller;

import com.google.common.collect.Maps;
import com.wizard.demo01.common.response.BaseResponse;
import com.wizard.demo01.common.response.StatusCode;
import com.wizard.demo01.common.utils.Constant;
import com.wizard.demo01.model.entity.SysMenuEntity;
import com.wizard.demo01.server.annotation.LogAnnotation;
import com.wizard.demo01.server.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author wizard_0992
 * @date 2019/12/16 9:52
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController{

    @Autowired
    private SysMenuService sysMenuService;

    //菜单列表
    @RequestMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public List<SysMenuEntity> list(){
        //第一种写法~借助mybatis-plus serviceImpl实现

//        List<SysMenuEntity> list=sysMenuService.list();
//
//        if (list!=null && !list.isEmpty()){
//            list.stream().forEach(entity -> {
//                /*if (Constant.MenuType.BUTTON.getValue() == entity.getType() ){
//                    SysMenuEntity menu=sysMenuService.getById(entity.getParentId());
//                    entity.setParentName( (menu!=null && StringUtils.isNotBlank(menu.getName()))? menu.getName() : "");
//                }*/
//
//                SysMenuEntity menu=sysMenuService.getById(entity.getParentId());
//                entity.setParentName( (menu!=null && StringUtils.isNotBlank(menu.getName()))? menu.getName() : "");
//            });
//        }
//
//        return list;

        //第二种方式~自己写sql
        return sysMenuService.queryAll();
    }



    //获取树形层级列表数据
    @RequestMapping("/select")
    public BaseResponse select(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            List<SysMenuEntity> list=sysMenuService.queryNotButtonList();

            SysMenuEntity root=new SysMenuEntity();
            root.setMenuId(Constant.TOP_MENU_ID);
            root.setName(Constant.TOP_MENU_NAME);
            root.setParentId(-1L);
            root.setOpen(true);
            list.add(root);

            resMap.put("menuList",list);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }

        response.setData(resMap);

        return response;
    }

    //新增
    @RequestMapping(value = "/save",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:menu:save")
    public BaseResponse save(@RequestBody SysMenuEntity entity){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            log.info("新增菜单~接收到数据：{}",entity);

            String result=this.validateForm(entity);
            if (StringUtils.isNotBlank(result)){
                return new BaseResponse(StatusCode.Fail.getCode(),result);
            }

            sysMenuService.save(entity);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;

    }


    //获取菜单详情
    @RequestMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public BaseResponse info(@PathVariable Long menuId){
        if (menuId==null || menuId<=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }

        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            resMap.put("menu",sysMenuService.getById(menuId));
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }

        response.setData(resMap);

        return response;
    }


    //修改
    @LogAnnotation("修改菜单")
    @RequestMapping(value = "/update",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:menu:update")
    public BaseResponse update(@RequestBody SysMenuEntity entity){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            log.info("修改菜单~接收到数据：{}",entity);

            String result=this.validateForm(entity);
            if (StringUtils.isNotBlank(result)){
                return new BaseResponse(StatusCode.Fail.getCode(),result);
            }

            sysMenuService.updateById(entity);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;

    }

    //删除
    @LogAnnotation("删除菜单")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @RequiresPermissions("sys:menu:delete")
    public BaseResponse delete(Long menuId){
        if (menuId==null || menuId<=0 ){
            return new BaseResponse(StatusCode.InvalidParams);
        }

        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            log.info("删除菜单~接收到数据：{}",menuId);

            SysMenuEntity entity=sysMenuService.getById(menuId);
            if (entity==null){
                return new BaseResponse(StatusCode.InvalidParams);
            }

            List<SysMenuEntity> list=sysMenuService.queryByParentId(entity.getMenuId());
            if (list!=null && !list.isEmpty()){
                return new BaseResponse(StatusCode.MenuHasSubMenuListCanNotDelete);
            }

            sysMenuService.delete(menuId);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }

        return response;
    }

    //验证参数是否正确
    private String validateForm(SysMenuEntity menu) {
        if (StringUtils.isBlank(menu.getName())) {
            return "菜单名称不能为空";
        }
        if (menu.getParentId() == null) {
            return "上级菜单不能为空";
        }

        //菜单
        if (menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                return "菜单链接url不能为空";
            }
        }

        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();

        if (menu.getParentId() != 0) {
            SysMenuEntity parentMenu = sysMenuService.getById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == Constant.MenuType.CATALOG.getValue() || menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (parentType != Constant.MenuType.CATALOG.getValue()) {
                return "上级菜单只能为目录类型";
            }
            return "";
        }

        //按钮
        if (menu.getType() == Constant.MenuType.BUTTON.getValue()) {
            if (parentType != Constant.MenuType.MENU.getValue()) {
                return "上级菜单只能为菜单类型";
            }
            return "";
        }

        return "";
    }



    //获取首页导航菜单列表
    @RequestMapping("/nav")
    public BaseResponse nav(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        Map<String,Object> resMap= Maps.newHashMap();
        try {
            List<SysMenuEntity> list=sysMenuService.getUserMenuList(getUserId());
            resMap.put("menuList",list);
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        response.setData(resMap);

        return response;
    }



}
