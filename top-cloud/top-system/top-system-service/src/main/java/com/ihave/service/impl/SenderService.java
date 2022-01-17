package com.ihave.service.impl;

import com.ihave.service.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/8/5 下午8:11
 */
@Service
public class SenderService {

    @Autowired
    private MySource source;

    /**
     * 添加路由
     */
    public <T> void addRoute(T msg){
        Message<T> message = MessageBuilder.withPayload(msg)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
        source.addRoute().send(message);
    }

    /**
     * 更新路由
     */
    public <T> void updateRoute(T msg){
        Message<T> message = MessageBuilder.withPayload(msg)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
        source.updateRoute().send(message);
    }

    /**
     * 删除路由
     * 参数是一个List<String>列表
     */
    public <T> void deleteRoute(T msg){
        Message<T> message = MessageBuilder.withPayload(msg)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
//                .setHeader(RocketMQHeaders.TAGS,tags)
//                .setHeader(RocketMQHeaders.KEYS,keys)
                .build();
        source.deleteRoute().send(message);
    }
}
