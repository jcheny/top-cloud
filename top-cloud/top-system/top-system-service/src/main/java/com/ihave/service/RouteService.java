package com.ihave.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ihave.entity.Route;

/**
* @author SENSETIME\xuxuangan
* @date 2021/7/29 上午11:13
* @version 1.0
*/
public interface RouteService extends IService<Route> {

    /**
     * 发布路由
     * @param id 路由id
     * @return
     */
    Boolean pushRouter(Long id);

    /**
     * 下线路由
     * @param id 路由id
     * @return
     */
    Boolean outlineRouter(Long id);
}
