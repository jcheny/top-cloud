//package com.sensemobile.service;
//
//import com.alibaba.fastjson.JSON;
//import com.sensemobile.routes.GatewayRoutes;
//import com.sensemobile.routes.GatewayServiceHandler;
//import com.sensemobile.service.MySource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
///**
// * @author SENSETIME\xuxuangan
// * @version 1.0
// * @date 2021/8/5 下午8:22
// */
//@Service
//@Slf4j
//public class ReceiveService {
//
//    @Resource
//    private GatewayServiceHandler handler;
//
//    @StreamListener(MySource.SAVE_ROUTE)
//    public void addRoute(@Payload GatewayRoutes route) {
//        log.info("addRoute 接收到了消息:" + JSON.toJSONString(route, true));
//        handler.saveRoute(route);
//        log.info("添加路由成功");
//    }
//
//    @StreamListener(MySource.UPDATE_ROUTE)
//    public void updateRoute(@Payload GatewayRoutes route) {
//        log.info("updateRoute 接收到了消息:" + JSON.toJSONString(route, true));
//        handler.updateRoute(route);
//        log.info("更新路由成功");
//    }
//
//    @StreamListener(MySource.DELETE_ROUTE)
//    public void deleteRoute(String id) {
//        log.info("deleteRoute 接收到了消息:" + id);
//        handler.deleteRoute(id);
//        log.info("删除路由成功");
//    }
//}
