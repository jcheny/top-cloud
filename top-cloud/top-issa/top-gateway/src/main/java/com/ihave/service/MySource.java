package com.ihave.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/5 下午8:21
 */
public interface MySource {

    String DELETE_ROUTE = "DELETE_ROUTE";
    String SAVE_ROUTE = "SAVE_ROUTE";
    String UPDATE_ROUTE = "UPDATE_ROUTE";

    @Input(MySource.SAVE_ROUTE)
    SubscribableChannel addRoute();

    @Input(MySource.UPDATE_ROUTE)
    SubscribableChannel updateRoute();

    @Input(MySource.DELETE_ROUTE)
    SubscribableChannel deleteRoute();
}
