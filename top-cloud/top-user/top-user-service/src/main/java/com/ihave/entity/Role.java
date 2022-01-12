package com.ihave.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author  SENSETIME\chenyu.vendor
 * @date  2021/7/21 下午3:09
 * @version 1.0
 */
/**
    * 角色
    */
@ApiModel(value="角色实体模型")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "top_role")
public class Role implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value="主键")
    private Long id;

    /**
     * 名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="名称")
    private String name;

    /**
     * 代码
     */
    @TableField(value = "code")
    @ApiModelProperty(value="代码")
    private String code;

    /**
     * 描述
     */
    @TableField(value = "description")
    @ApiModelProperty(value="描述")
    private String description;

    /**
     * 状态[0:未删除,1:删除]
     */
    @TableLogic
    @TableField(value = "is_deleted")
    @ApiModelProperty(value="状态[0:未删除,1:删除]")
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

    public static final String COL_NAME = "name";

    public static final String COL_CODE = "code";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_CREATE_USER = "create_user";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_UPDATE_USER = "update_user";
}