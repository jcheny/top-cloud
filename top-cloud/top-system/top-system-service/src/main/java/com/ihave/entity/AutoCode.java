package com.ihave.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "top_auto_code")
@ApiModel(value = "")
public class AutoCode implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private Long id;


    @TableField(value = "`table_name`")
    @ApiModelProperty(value = "数据库表名")
    private String tableName;

    @TableField(value = "`model_name`")
    @ApiModelProperty(value = "模块名")
    private String modelName;

    @TableField(value = "`gateway_name`")
    @ApiModelProperty(value = "api网关名称")
    private String gatewayName;

    @TableField(value = "`vue_name`")
    @ApiModelProperty(value = "前端模块名")
    private String vueName;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;


}