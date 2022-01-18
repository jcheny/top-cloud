package com.ihave.routes;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author SENSETIME\chenyu.vendor
 * @date 2021/7/27 下午6:53
 * @version 1.0
 */

/**
 * 网关路由表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayRoutes implements Serializable {

    private Long id;

    /**
     * 路由id
     */
    private String routeId;

    /**
     * 转发目标uri
     */
    private String routeUri;

    /**
     * 路由执行顺序
     */
    private Integer routeOrder;

    /**
     * 访问路径
     */
    private String predicates;

    /**
     * 过滤
     */
    private String filters;

    /**
     * 是否统计
     */
    private Integer isStatistic;

    /**
     * 是否计费
     */
    private Integer isBilling;

    /**
     * 是否启用
     */
    private Integer isEbl;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}