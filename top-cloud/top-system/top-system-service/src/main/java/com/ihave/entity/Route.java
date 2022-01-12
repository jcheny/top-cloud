package com.ihave.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author SENSETIME\xuxuangan
* @date 2021/7/29 上午11:13
* @version 1.0
*/
/**
    * 网关路由表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "com-ihave-entity-Route")
@TableName(value = "top_gateway_routes")
public class Route implements Serializable {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 路由id
     */
    @TableField(value = "route_id")
    @ApiModelProperty(value="路由id")
    private String routeId;

    /**
     * 转发目标uri
     */
    @TableField(value = "route_uri")
    @ApiModelProperty(value="转发目标uri")
    private String routeUri;

    /**
     * 路由执行顺序
     */
    @TableField(value = "route_order")
    @ApiModelProperty(value="路由执行顺序")
    private Integer routeOrder;

    /**
     * 访问路径
     */
    @TableField(value = "predicates")
    @ApiModelProperty(value="访问路径")
    private String predicates;

    /**
     * 过滤
     */
    @TableField(value = "filters")
    @ApiModelProperty(value="过滤")
    private String filters;

    /**
     * 是否统计
     */
    @TableField(value = "is_statistic")
    @ApiModelProperty(value="是否统计")
    private Boolean isStatistic;

    /**
     * 是否计费
     */
    @TableField(value = "is_billing")
    @ApiModelProperty(value="是否计费")
    private Boolean isBilling;

    /**
     * 是否启用
     */
    @TableField(value = "is_ebl")
    @ApiModelProperty(value="是否启用")
    private Integer isEbl;

    /**
     * 0未删，1删除
     */
    @TableField(value = "is_deleted")
    @ApiModelProperty(value="0未删，1删除")
    @TableLogic
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_ROUTE_ID = "route_id";

    public static final String COL_ROUTE_URI = "route_uri";

    public static final String COL_ROUTE_ORDER = "route_order";

    public static final String COL_PREDICATES = "predicates";

    public static final String COL_FILTERS = "filters";

    public static final String COL_IS_STATISTIC = "is_statistic";

    public static final String COL_IS_BILLING = "is_billing";

    public static final String COL_IS_EBL = "is_ebl";

    public static final String COL_IS_DEL = "is_del";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}