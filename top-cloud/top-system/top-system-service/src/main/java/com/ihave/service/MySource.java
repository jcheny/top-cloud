//package com.sensemobile.service;
//
//import com.sensemobile.constants.SysConstant;
//import org.springframework.cloud.stream.annotation.Output;
//import org.springframework.messaging.MessageChannel;
//
///**
// * @author SENSETIME\xuxuangan
// * @version 1.0
// * @date 2021/8/5 下午8:10
// */
//public interface MySource {
//
//    /**
//     * 添加路由
//     * @return
//     */
//    @Output(SysConstant.SAVE_ROUTE)
//    MessageChannel addRoute();
//
//    /**
//     * 更新路由
//     * @return
//     */
//    @Output(SysConstant.UPDATE_ROUTE)
//    MessageChannel updateRoute();
//
//    /**
//     * 删除路由
//     * @return
//     */
//    @Output(SysConstant.DELETE_ROUTE)
//    MessageChannel deleteRoute();
//}
