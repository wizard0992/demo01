package com.wizard.demo01.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @author wizard_0992
 * @date 2019/12/2 14:23
 * exist = false(非数据库内的固有字段)
 */
@Data
@TableName("attend_record")
public class AttendRecordEntity implements Serializable{

    @TableId
    private Integer id;

    private Long userId;

    private Long deptId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    private BigDecimal total;

    private Byte status;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;


    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String deptName;

}