package com.ihave.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@TableName(value = "top_menu")
@ApiModel(value="系统菜单")
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

	@TableId(value = "id",type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键")
	private Long id;

		
	@TableField(value = "`parent_id`")
	@ApiModelProperty(value = "上级菜单ID")
	private Long parentId;
	
	@TableField(value = "`key`")
	@ApiModelProperty(value = "上级菜单唯一KEY值")
	private String key;
	
	@TableField(value = "`type`")
	@ApiModelProperty(value = "类型 1-分类 2-节点")
	private Integer type;
	
	@TableField(value = "`name`")
	@ApiModelProperty(value = "名称")
	private String name;
	
	@TableField(value = "`desc`")
	@ApiModelProperty(value = "描述")
	private String desc;

	@TableField(value = "`icon`")
	@ApiModelProperty(value = "图标")
	private String icon;
	
	@TableField(value = "`target_url`")
	@ApiModelProperty(value = "目标地址")
	private String targetUrl;

	@TableField(value = "`shows`")
	@ApiModelProperty(value = "是否展示（0：展示，1不展示）")
	private Integer shows;
	
	@TableField(value = "`sort`")
	@ApiModelProperty(value = "排序索引")
	private Integer sort;
	
	@TableField(value = "`status`")
	@ApiModelProperty(value = "状态 0-有效； 1-无效；")
	private Integer status;
	
	@TableField(value = "`create_user`")
	@ApiModelProperty(value = "创建人")
	private Long createUser;
	
	@TableField(value = "`update_user`")
	@ApiModelProperty(value = "修改人")
	private Long updateUser;

		@ApiModelProperty(value = "创建时间")
	@TableField(value = "create_time")
	private Date createTime;


		@ApiModelProperty(value = "修改时间")
	@TableField(value = "update_time")
	private Date updateTime;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		Menu other = (Menu) obj;

		if (Objects.equals(this.getId(), other.getId())) {
			return true;
		}
		return false;
	}

}