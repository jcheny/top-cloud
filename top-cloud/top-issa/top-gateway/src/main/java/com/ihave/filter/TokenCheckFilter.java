package com.ihave.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

/**
 * 判断请求是否携带token
 *
 * @Author: chen
 * @Date: 2021/4/9 2:24 PM
 * @Version 1.0
 */
@Component
@EnableConfigurationProperties(IgnoreAccessToken.class)
public class TokenCheckFilter implements GlobalFilter, Ordered {

    private final IgnoreAccessToken ignoreAccessToken;

    public TokenCheckFilter(IgnoreAccessToken ignoreAccessToken) {
        this.ignoreAccessToken = ignoreAccessToken;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    /**
     * 实现判断用户是否携带token ，或token 错误的功能
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 不需要token 就能访问
        if (allowNoTokenAccess(exchange)) {
            return chain.filter(exchange);
        }
        // 获取用户的token
        String token = getToken(exchange);

        if (StringUtils.isEmpty(token)) { // token 为 Empty
            return buildUNAuthorizedResult(exchange);
        }
        return chain.filter(exchange);
    }

    /**
     * 判断不需要token的uri
     *
     * @param exchange
     * @return
     */
    private boolean allowNoTokenAccess(ServerWebExchange exchange) {
        String path = exchange.getRequest().getURI().getPath();
        List<String> allowPaths = ignoreAccessToken.getAllowPaths();
        for (String str : allowPaths) {
            if (StringUtils.containsAny(str, "\\*\\*")) {
                int i = str.indexOf("**");
                String substring = str.substring(0, i - 1);
                if (path.contains(substring)) {
                    return true;
                }
            } else {
                if (path.equals(str)) {
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * 从头里面获取
     *
     * @param exchange
     * @return
     */
    private String getToken(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String authorization = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (Objects.isNull(authorization) || authorization.trim().isEmpty()) {
            return null;
        }
        return authorization.replace("bearer ", "");
    }

    /**
     * 响应没有认证的结果
     *
     * @param exchange
     * @return
     */
    private Mono<Void> buildUNAuthorizedResult(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().set("Content-Type", "application/json;charset=UTF-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "unauthorized");
        jsonObject.put("code", "401");
        jsonObject.put("success", "false");
        jsonObject.put("message", "非法token");
        DataBuffer dataBuffer = response.bufferFactory().wrap(jsonObject.toJSONString().getBytes());
        return response.writeWith(Flux.just(dataBuffer));
    }
}