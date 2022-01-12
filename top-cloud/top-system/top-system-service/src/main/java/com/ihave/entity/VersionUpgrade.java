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
@TableName(value = "top_version_upgrade")
@ApiModel(value="apk版本表")
public class VersionUpgrade implements Serializable {
    private static final long serialVersionUID = 1L;

	
	@TableId(value = "`id`",type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private Long id;

	@TableField(value = "app_id")
	@ApiModelProperty(value = "app")
	private String appId;

	@TableField(value = "name")
	@ApiModelProperty(value = "name")
	private String name;
	
	@TableField(value = "`version_id`")
	@ApiModelProperty(value = "大版本号id")
	private Integer versionId;
	
	@TableField(value = "`version_mini`")
	@ApiModelProperty(value = "小版本号")
	private Integer versionMini;
	
	@TableField(value = "`version_code`")
	@ApiModelProperty(value = "版本标识 1.2")
	private String versionCode;
	
	@TableField(value = "`type`")
	@ApiModelProperty(value = "是否升级 1升级，0不升级，2强制升级")
	private Integer type;
	
	@TableField(value = "`apk_url`")
	@ApiModelProperty(value = "安装包url")
	private String apkUrl;
	
	@TableField(value = "`upgrade_point`")
	@ApiModelProperty(value = "升级提示")
	private String upgradePoint;
	
	@TableField(value = "`status`")
	@ApiModelProperty(value = "状态[0:正常,1:停用]")
	private Integer status;

	@TableField(value = "is_deleted")
	@ApiModelProperty(value="状态[0:未删除,1:删除]")
	@TableLogic
	private Integer isDeleted;

	@TableField(value = "create_time",fill = FieldFill.INSERT)
	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;

}