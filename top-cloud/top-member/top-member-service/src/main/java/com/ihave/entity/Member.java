package com.ihave.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "top_member")
@ApiModel(value = "会员模型")
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private Long id;

    @TableField(value = "`username`")
    @ApiModelProperty(value = "用户名")
    private String username;

    @TableField(value = "`mobile`")
    @ApiModelProperty(value = "电话")
    private String mobile;

    @TableField(value = "`country_code`")
    @ApiModelProperty(value = "手机区号")
    private String countryCode;

    @TableField(value = "`avatar`")
    @ApiModelProperty(value = "头像")
    private String avatar;

    @TableField(value = "`password`")
    @ApiModelProperty(value = "密码")
    private String password;

    @TableField(value = "`sex`")
    @ApiModelProperty(value = "性别（0：女，1：男）")
    private Integer sex;

    @TableField(value = "`age`")
    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "生日")
    @TableField(value = "birthday")
    private Date birthday;


    @TableField(value = "`status`")
    @ApiModelProperty(value = "是否可用（0：可用，1：不可用）")
    private Integer status;

    @TableField(value = "`is_deleted`")
    @TableLogic
    @ApiModelProperty(value = "是否删除（0：未删除，1：已删除）")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}