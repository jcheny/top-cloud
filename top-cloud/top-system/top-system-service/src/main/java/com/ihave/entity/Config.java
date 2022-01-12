package com.ihave.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author SENSETIME\xuxuangan
* @date 2021/7/29 上午9:34
* @version 1.0
*/
/**
    * 平台配置信息
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "com-ihave-entity-Config")
@TableName(value = "top_config")
public class Config implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value="主键")
    private Long id;

    /**
     * 配置规则类型
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value="配置规则类型")
    private String type;

    /**
     * 配置规则代码
     */
    @TableField(value = "code")
    @ApiModelProperty(value="配置规则代码")
    private String code;

    /**
     * 配置规则名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="配置规则名称")
    private String name;

    /**
     * 配置规则描述
     */
    @TableField(value = "`desc`")
    @ApiModelProperty(value="配置规则描述")
    private String desc;

    /**
     * 配置值
     */
    @TableField(value = "`value`")
    @ApiModelProperty(value="配置值")
    private String value;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_TYPE = "type";

    public static final String COL_CODE = "code";

    public static final String COL_NAME = "name";

    public static final String COL_DESC = "desc";

    public static final String COL_VALUE = "value";

    public static final String COL_CREATE_TIME = "create_time";
}