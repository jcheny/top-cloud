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
@TableName(value = "top_role_menu")
@ApiModel(value="角色菜单")
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = 1L;

	@TableId(value = "id",type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private Long id;

	@TableField(value = "`role_id`")
	@ApiModelProperty(value = "角色id")
	private Long roleId;
	
	@TableField(value = "`menu_id`")
	@ApiModelProperty(value = "菜单id")
	private Long menuId;
	
	@TableField(value = "`create_user`",fill = FieldFill.INSERT)
	@ApiModelProperty(value = "创建人")
	private Long createUser;
	
	@TableField(value = "`update_user`",fill = FieldFill.INSERT_UPDATE)
	@ApiModelProperty(value = "修改人")
	private Long updateUser;

	@ApiModelProperty(value = "创建时间")
	@TableField(value = "create_time",fill = FieldFill.INSERT)
	private Date createTime;


	@ApiModelProperty(value = "修改时间")
	@TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	public static RoleMenu build(Long roleId,Long menuId){
		RoleMenu roleMenu = new RoleMenu();
		roleMenu.setRoleId(roleId);
		roleMenu.setMenuId(menuId);
		return roleMenu;
	}
}