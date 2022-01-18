package com.ihave.service.impl;

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

import javax.annotation.Resource;
import java.util.List;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/7/29 上午11:13
 */
@Service
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Route> implements RouteService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Resource
    private SenderService senderService;


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


    @Override
    @Transactional
    public Boolean pushRouter(Long id) {
        Route route = getById(id);
        extracted(route.getRouteId());
        senderService.addRoute(route);
        route.setIsEbl(0);
        updateById(route);
        return true;
    }

    @Override
    @Transactional
    public Boolean outlineRouter(Long id) {
        Route route = getById(id);
        senderService.deleteRoute(route.getRouteId());
        route.setIsEbl(1);
        updateById(route);
        return true;
    }
}
