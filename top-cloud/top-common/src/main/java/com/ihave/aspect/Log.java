package com.ihave.aspect;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author SENSETIME\xuxuangan
 * @date 2021/7/29 下午2:55
 * @version 1.0
 */

/**
 * 系统日志
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "日志模型")
@TableName(value = "top_log")
public class Log implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 组
     */
    @TableField(value = "`group`")
    @ApiModelProperty(value = "组")
    private String group;

    /**
     * 用户Id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户Id")
    private Long userId;

    /**
     * 日志类型 1`INFO 2`DEBUG 3`WARING 4`ERROR
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value = "日志类型")
    private Integer type;

    /**
     * 方法
     */
    @TableField(value = "method")
    @ApiModelProperty(value = "方法")
    private String method;

    /**
     * 参数
     */
    @TableField(value = "params")
    @ApiModelProperty(value = "参数")
    private String params;

    /**
     * 时间
     */
    @TableField(value = "time")
    @ApiModelProperty(value = "时间")
    private Long time;

    /**
     * IP地址
     */
    @TableField(value = "ip")
    @ApiModelProperty(value = "ip地址")
    private String ip;

    /**
     * 用户设备
     */
    @TableField(value = "agent")
    @ApiModelProperty(value = "用户设备")
    private String agent;

    /**
     * 用户设备
     */
    @TableField(value = "url")
    @ApiModelProperty(value = "用户接口")
    private String url;


    /**
     * 描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    private static final long serialVersionUID = 1L;
}