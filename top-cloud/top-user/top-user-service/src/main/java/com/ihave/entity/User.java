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
    * 用户表
    */
@ApiModel(value="管理用户模型")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "top_user")
public class User implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 编号
     */
    @TableField(value = "code")
    @ApiModelProperty(value="编号")
    private String code;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @ApiModelProperty(value="用户名")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 国际电话区号
     */
    @TableField(value = "country_code")
    @ApiModelProperty(value="国际电话区号")
    private String countryCode;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    @ApiModelProperty(value="手机号")
    private String mobile;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    @ApiModelProperty(value="头像")
    private String avatar;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    @ApiModelProperty(value="生日")
    private String birthday;

    /**
     * 性别[0:女,1:男]
     */
    @TableField(value = "sex")
    @ApiModelProperty(value="性别[0:女,1:男]")
    private Integer sex;

    /**
     * 状态[0:未删除,1:删除]
     */
    @TableField(value = "is_deleted")
    @ApiModelProperty(value="状态[0:未删除,1:删除]")
    @TableLogic
    private Integer isDeleted;

    /**
     * 状态[0:正常,1:冻结]
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="状态[0:正常,1:冻结]")
    private Integer status;

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
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    /**
     * 更新人
     */
    @TableField(value = "update_user",fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="更新人")
    private Long updateUser;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CODE = "code";

    public static final String COL_USERNAME = "username";

    public static final String COL_PASSWORD = "password";

    public static final String COL_COUNTRY_CODE = "country_code";

    public static final String COL_MOBILE = "mobile";

    public static final String COL_EMAIL = "email";

    public static final String COL_AVATAR = "avatar";

    public static final String COL_BIRTHDAY = "birthday";

    public static final String COL_SEX = "sex";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_CREATE_USER = "create_user";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_UPDATE_USER = "update_user";
}