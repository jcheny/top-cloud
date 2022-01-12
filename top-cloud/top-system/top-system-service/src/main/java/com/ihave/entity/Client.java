package com.ihave.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/** 
* @author SENSETIME\xuxuangan
* @date 2021/7/28 下午5:28
* @version 1.0
*/
/**
    * 客户端表
    */
@Data
@ApiModel(value = "com-ihave-entity-Client")
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "top_oauth_client_details")
public class Client implements Serializable {
    /**
    * 用于唯一标识每一个客户端
    */
    @TableField(value = "client_id")
    @ApiModelProperty(value="clientId")
    private String clientId;

    /**
    * 客户端所能访问的资源id集合,多个资源时用逗号(,)分隔
    */
    @TableField(value = "resource_ids")
    @ApiModelProperty(value="")
    private String resourceIds;

    /**
     * 用于指定客户端(client)的访问密匙明文
     */
    @TableField(value = "client_secret_name")
    @ApiModelProperty(value="")
    private String clientSecretName;

    /**
    * 用于指定客户端(client)的访问密匙
    */
    @TableField(value = "client_secret")
    @ApiModelProperty(value="")
    private String clientSecret;

    /**
    * 指定客户端申请的权限范围
    */
    @TableField(value = "scope")
    @ApiModelProperty(value="")
    private String scope;

    /**
    * 指定客户端支持的grant_type
    */
    @TableField(value = "authorized_grant_types")
    @ApiModelProperty(value="")
    private String authorizedGrantTypes;

    /**
    * 客户端的重定向URI,可为空
    */
    @TableField(value = "web_server_redirect_uri")
    @ApiModelProperty(value="")
    private String webServerRedirectUri;

    /**
    * 指定客户端所拥有的Spring Security的权限值
    */
    @TableField(value = "authorities")
    @ApiModelProperty(value="")
    private String authorities;

    /**
    * 设定客户端的access_token的有效时间值(单位:秒)
    */
    @TableField(value = "access_token_validity")
    @ApiModelProperty(value="")
    private Integer accessTokenValidity;

    /**
    * 设定客户端的refresh_token的有效时间值(单位:秒)
    */
    @TableField(value = "refresh_token_validity")
    @ApiModelProperty(value="")
    private Integer refreshTokenValidity;

    /**
    * 预留的字段
    */
    @TableField(value = "additional_information")
    @ApiModelProperty(value="")
    private String additionalInformation;

    /**
    * 设置用户是否自动Approval操作
    */
    @TableField(value = "autoapprove")
    @ApiModelProperty(value="")
    private String autoapprove;

    /**
    * 创建时间
    */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(value="")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_CLIENT_ID = "client_id";
}