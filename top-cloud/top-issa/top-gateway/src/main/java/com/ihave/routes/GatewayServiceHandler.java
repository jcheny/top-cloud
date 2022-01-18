package com.ihave.routes;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/27 下午6:44
 */
@Service
@Slf4j
public class GatewayServiceHandler implements ApplicationEventPublisherAware, CommandLineRunner {
    @Autowired
    private RedisRouteDefinitionRepository routeDefinitionWriter;

    private ApplicationEventPublisher publisher;

    private static final String BASE_COLUMN = "id,route_id as routeId,route_uri as routeUri,route_order as routeOrder ,predicates,filters,is_statistic as isStatistic,is_billing as isBilling,is_ebl as isEbl, create_time as createTime,update_time as updateTime ";

    private static final String QUERY_GATEWAY_ROUTES = "select " + BASE_COLUMN + " from top_gateway_routes where is_ebl = 0";

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * springboot启动后执行 相当于@PostConstruct
     *
     * @param args
     */
    @Override
    public void run(String... args) {
        this.loadRouteConfig();
    }

    /**
     * 将数据库的路由配置加载到redis，并刷新路由
     */
    public void loadRouteConfig() {
        log.info("====开始加载=====网关配置信息=========");
        // 1. 首先删除redis里面已经存在的路由配置信息
        routeDefinitionWriter.deleteAll();
        // 2. 从数据库拿到基本路由配置
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(QUERY_GATEWAY_ROUTES);
        List<GatewayRoutes> gatewayRouteList = maps.stream().map(stringObjectMap -> {
            GatewayRoutes gatewayRoutes = new GatewayRoutes();
            try {
                BeanUtils.populate(gatewayRoutes, stringObjectMap);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return gatewayRoutes;
        }).collect(Collectors.toList());
        log.info("数据库网关配置信息：=====>" + JSON.toJSONString(gatewayRouteList, true));
        // 3. 将数据库读取的路由配置，转换为RouteDefinition，并保存到redis
        gatewayRouteList.forEach(gatewayRoute -> {
            RouteDefinition definition = handleData(gatewayRoute);
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        });
        // 4. 刷新路由
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        log.info("=======网关配置信息===加载完成======");
    }

    /**
     * 保存路由，并刷新路由信息
     *
     */
    public void saveRoute(GatewayRoutes gatewayRoute) {
        RouteDefinition definition = handleData(gatewayRoute);
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    /**
     * 更新路由，并刷新路由信息
     *
     */
    public void updateRoute(GatewayRoutes gatewayRoute) {
        RouteDefinition definition = handleData(gatewayRoute);
        routeDefinitionWriter.delete(Mono.just(definition.getId())).subscribe();
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    /**
     * 删除路由，并刷新路由信息
     *
     */
    public void deleteRoute(String routeId) {
        routeDefinitionWriter.delete(Mono.just(routeId)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }


    /**
     * 路由数据转换的公共方法
     *
     */
    private RouteDefinition handleData(GatewayRoutes gatewayRoute) {
        RouteDefinition definition = new RouteDefinition();
        Map<String, String> predicateParams = new HashMap<>(8);
        PredicateDefinition predicate = new PredicateDefinition();
        FilterDefinition filterDefinition = new FilterDefinition();
        Map<String, String> filterParams = new HashMap<>(8);

        URI uri = null;
        if (gatewayRoute.getRouteUri().startsWith("http")) {
            //http地址
            uri = UriComponentsBuilder.fromHttpUrl(gatewayRoute.getRouteUri()).build().toUri();
        } else {
            //注册中心
            uri = URI.create(gatewayRoute.getRouteUri());
        }
        // 使用routeId作为definition的id
        definition.setId(gatewayRoute.getRouteId());

        // 使用数据库的id字段
        // definition.setId(gatewayRoute.getId().toString());

        // 名称是固定的，spring gateway会根据名称找对应的PredicateFactory
        predicate.setName("Path");
        predicateParams.put("pattern", gatewayRoute.getPredicates());
        predicate.setArgs(predicateParams);

        // 名称是固定的, 路径去前缀
        filterDefinition.setName("StripPrefix");
        filterParams.put("_genkey_0", gatewayRoute.getFilters().toString());
        filterDefinition.setArgs(filterParams);

        definition.setPredicates(Collections.singletonList(predicate));
        definition.setFilters(Collections.singletonList(filterDefinition));
        definition.setUri(uri);
        return definition;
    }
}
