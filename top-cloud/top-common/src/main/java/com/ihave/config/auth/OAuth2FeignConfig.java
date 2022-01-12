package com.ihave.config.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Author: chen
 * @Date: 2021/4/29 2:39 PM
 * @Version 1.0
 */
@Configuration
public class OAuth2FeignConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //从上线文中获取token
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        String header = requestAttributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
        requestTemplate.header(HttpHeaders.AUTHORIZATION, header);
    }

}
