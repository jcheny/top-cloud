package com.ihave.controller;

import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @Author: chen
 * @Date: 2021/4/9 11:09 AM
 * @Version 1.0
 */
@RestController
public class FlowRulesController {

    /**
     * 获取当前系统的限流策略
     *
     * @return
     */
    @GetMapping("/gateway")
    public Set<GatewayFlowRule> getGatewayFlowRules() {
        return GatewayRuleManager.getRules();
    }

    /**
     * 获取定义的API分组
     *
     * @return
     */
    @GetMapping("/api")
    public Set<ApiDefinition> getApiGroupRules() {
        return GatewayApiDefinitionManager.getApiDefinitions();
    }
}