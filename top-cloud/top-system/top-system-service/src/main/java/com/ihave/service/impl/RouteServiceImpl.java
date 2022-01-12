package com.ihave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihave.entity.Route;
import com.ihave.mapper.RouteMapper;
import com.ihave.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/7/29 上午11:13
 */
@Service
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Route> implements RouteService {

    private static final String ROUTE_PREFIX = "lb://";

    @Resource
    private RouteMapper routeMapper;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Resource
    private SenderService senderService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Route saveRoute(Route route) {
        // 先验证路由是否有效
        validateRoute(route);
        route.setFilters("1");
        try {
            routeMapper.insert(route);
            return route;
        } catch (Exception e) {
            throw new ApiException(e);
        }
    }

    /**
     * 1. 首先检查参数routeId是否有效，若有效的话，则设置routeUri
     * 2. 检查是否重复
     * 3. 检查当前服务是否上线
     */
    private void validateRoute(Route route){
        if(checkParam(route)){
            route.setRouteUri(ROUTE_PREFIX+route.getRouteId());
        }
        checkRepeat(route);
        extracted(route.getRouteId());
    }

    /**
     * 检查参数是否符合规则
     * @return
     */
    private boolean checkParam(Route route){
        String routeId = route.getRouteId();
        if(StringUtils.isEmpty(routeId) || routeId.startsWith(ROUTE_PREFIX) || routeId.contains("/")) {
            throw new ApiException("Route参数routeId无效");
        }
        String routePredicates = route.getPredicates();
        if(StringUtils.isEmpty(routePredicates)){
            throw new ApiException("Route参数predicates无效");
        }
        return true;
    }

    /**
     * 检查该服务是否已经上线
     * @param routeId
     */
    private void extracted(String routeId) {
        List<ServiceInstance> instances = discoveryClient.getInstances(routeId);
        if (CollectionUtils.isEmpty(instances)) {
            throw new ApiException("请先上线该服务在做配置");
        }
    }

    /**
     * 检查路由是否已经存在
     */
    private void checkRepeat(Route route){
        QueryWrapper<Route> routeWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(route.getRouteId())) {
            routeWrapper.or().eq("route_id", route.getRouteId());
        }
        if (!StringUtils.isEmpty(route.getPredicates())) {
            route.setPredicates("/" + route.getPredicates() + "/**");
            routeWrapper.or().eq("predicates", route.getPredicates());
        }
        List<Route> repeat = routeMapper.selectList(routeWrapper);
        if (repeat.size() > 0) {
            throw new ApiException("路由已经重复存在");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRouteByIds(List<String> routeIds) {
        try {
            for (String routeId : routeIds) {
                extracted(routeId);
                routeMapper.delete(new QueryWrapper<Route>()
                        .eq("route_id", routeId));
//                senderService.deleteRoute(routeId);
            }
            return true;
        } catch (Exception e) {
            throw new ApiException(e);
        }
    }

    /**
     * 修改路由的信息：routeId或者predicates或者其他信息，但不能修改uri（uri由routeId添加前缀生成）
     * 1. 检查route参数是否有效，若有效的话，则根据routeId更新uri
     * 2. 检查路由是否已在线上
     * @param route
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object updateRoute(Route route) {
        route.setFilters("1");
        if(checkParam(route)){
            checkRepeat(route);
            route.setRouteUri(ROUTE_PREFIX+route.getRouteId());
        }
        extracted(route.getRouteId());
        this.updateById(route);
        return route;
    }
}
