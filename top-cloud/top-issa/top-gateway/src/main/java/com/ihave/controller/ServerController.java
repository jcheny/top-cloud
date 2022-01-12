package com.ihave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/6 下午4:49
 */
@RestController
@RequestMapping("/server")
public class ServerController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping
    public Object ins() {
        return discoveryClient.getServices();
    }

    @GetMapping("/{serviceId}")
    public Object instances(@PathVariable String serviceId) {
        return discoveryClient.getInstances(serviceId);
    }
}
