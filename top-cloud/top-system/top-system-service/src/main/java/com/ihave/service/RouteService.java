package com.ihave.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ihave.entity.Route;

import java.util.List;

/**
* @author SENSETIME\xuxuangan
* @date 2021/7/29 上午11:13
* @version 1.0
*/
public interface RouteService extends IService<Route> {

    /**
     * 保存route
     * @param route
     * @return
     */
    Route saveRoute(Route route);

    /**
     * 根据routeId删除路由信息
     * @param routeIds
     * @return
     */
    boolean deleteRouteByIds(List<String> routeIds);

    /**
     * 更新route
     * @param route
     * @return
     */
    Object updateRoute(Route route);
}
