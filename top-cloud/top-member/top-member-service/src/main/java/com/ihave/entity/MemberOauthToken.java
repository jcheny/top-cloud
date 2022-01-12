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
@TableName(value = "top_member_oauth_token")
@ApiModel(value = "会员第三方客户端token表")
public class MemberOauthToken implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private Long id;


    @TableField(value = "`member_oauth_id`")
    @ApiModelProperty(value = "第三方用户id")
    private Long memberOauthId;

    @TableField(value = "`access_token`")
    @ApiModelProperty(value = "token")
    private String accessToken;

    @TableField(value = "`expire_in`")
    @ApiModelProperty(value = "过期时间")
    private Integer expireIn;

    @TableField(value = "`refresh_token`")
    @ApiModelProperty(value = "刷新token")
    private String refreshToken;

    @TableField(value = "`scope`")
    @ApiModelProperty(value = "平台授权范围")
    private String scope;

    @TableField(value = "`source`")
    @ApiModelProperty(value = "来源")
    private String source;

    @TableField(value = "`status`")
    @ApiModelProperty(value = "状态[0:正常使用,1:过期]")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}