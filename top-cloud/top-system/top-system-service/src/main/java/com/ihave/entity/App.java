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
@TableName(value = "top_app")
@ApiModel(value = "app模型")
public class App implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private Long id;


    @TableField(value = "`app_name`")
    @ApiModelProperty(value = "app名称")
    private String appName;

    @TableField(value = "`status`")
    @ApiModelProperty(value = "状态")
    private Integer status;

    @TableField(value = "`privacy_agreement`")
    @ApiModelProperty(value = "隐私协议")
    private String privacyAgreement;

    @TableField(value = "`user_agreement`")
    @ApiModelProperty(value = "隐私协议")
    private String userAgreement;

    @TableField(value = "`is_deleted`")
    @ApiModelProperty(value = "是否删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}