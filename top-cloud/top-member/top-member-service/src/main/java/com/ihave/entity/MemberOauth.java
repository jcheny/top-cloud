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
@TableName(value = "top_member_oauth")
@ApiModel(value = "会员用户第三方认证表")
public class MemberOauth implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private Long id;


    @TableField(value = "`uuid`")
    @ApiModelProperty(value = "第三方系统用户ID")
    private String uuid;

    @TableField(value = "`member_id`")
    @ApiModelProperty(value = "系统用户主键")
    private Long memberId;

    @TableField(value = "`username`")
    @ApiModelProperty(value = "用户名")
    private String username;

    @TableField(value = "`nickname`")
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @TableField(value = "`avatar`")
    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @TableField(value = "`blog`")
    @ApiModelProperty(value = "用户网址")
    private String blog;

    @TableField(value = "`company`")
    @ApiModelProperty(value = "所在公司")
    private String company;

    @TableField(value = "`location`")
    @ApiModelProperty(value = "位置")
    private String location;

    @TableField(value = "`email`")
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @TableField(value = "`remark`")
    @ApiModelProperty(value = "用户备注（各平台中的用户个人介绍）")
    private String remark;

    @TableField(value = "`gender`")
    @ApiModelProperty(value = "性别")
    private String gender;

    @TableField(value = "`source`")
    @ApiModelProperty(value = "用户来源")
    private String source;

    @TableField(value = "`is_deleted`")
    @TableLogic
    @ApiModelProperty(value = "状态[0:未删除,1:删除]")
    private Integer isDeleted;

    @TableField(value = "`status`")
    @ApiModelProperty(value = "状态[0:正常,1:冻结]")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    private Date createTime;


    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time")
    private Date updateTime;


}