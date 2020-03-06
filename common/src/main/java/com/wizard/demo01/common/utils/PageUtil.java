package com.wizard.demo01.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询and模糊查询
 * 统一封装的分页工具类 ：也可以指定 long类型；但是一般int就够用了 ~ 根据具体的业务情况而定!
 * @author wizard_0992
 * @date 2020/2/26 22:03
 */
@Data
@ToString
public class PageUtil implements Serializable {

    //总的记录数
    private long totalCount;

    //每页显示记录数
    private long pagesize;

    //总的页数
    private long totalPage;

    //当前页数
    private long currPage;

    //数据列表
    private List<?> list;

    public PageUtil(long totalCount, long pagesize, long totalPage, long currPage, List<?> list) {
        this.totalCount = totalCount;
        this.pagesize = pagesize;
        this.totalPage = totalPage;
        this.currPage = currPage;
        this.list = list;
    }

    public PageUtil(IPage<?> page) {
        this.totalCount = page.getTotal();
        this.pagesize = page.getSize();
        this.totalPage = page.getPages();
        this.currPage = page.getCurrent();
        this.list = page.getRecords();
    }
}
