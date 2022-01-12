package com.ihave.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author  SENSETIME\chenyu.vendor
 * @date  2021/7/21 下午3:09
 * @version 1.0
 */
/**
    * 用户角色配置
    */
@ApiModel(value="用户角色模型")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "top_user_role")
public class UserRole implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    @ApiModelProperty(value="主键")
    private Long id;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value="角色ID")
    private Long roleId;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户ID")
    private Long userId;

    /**
     * 状态[0:未删除,1:删除]
     */
    @TableField(value = "is_deleted")
    @ApiModelProperty(value="状态[0:未删除,1:删除]")
    @TableLogic
    private Byte isDeleted;

    /**
     * 状态[0:正常,1:冻结]
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="状态[0:正常,1:冻结]")
    private Byte status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user",fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建人")
    private Long createUser;

    /**
     * 修改时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="修改时间")
    private Date updateTime;

    /**
     * 修改人
     */
    @TableField(value = "update_user",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="修改人")
    private Long updateUser;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_CREATE_USER = "create_user";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_UPDATE_USER = "update_user";
}