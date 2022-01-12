package com.ihave.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @Author: chen
 * @Date: 2021/4/9 2:17 PM
 * @Version 1.0
 */
@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * 资源的放行
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().disable()
                //比配路径  以下路径不需要携带token 即可访问
                .authorizeRequests().antMatchers(
                "/v2/api-docs",
                "/login",
                "/users/register",
                "/gt/register",
                "/swagger-resources/configuration/ui",//用来获取支持的动作
                "/swagger-resources",//用来获取api-docs的URI
                "/swagger-resources/configuration/security",//安全选项
                "/webjars/**",
                "/doc.html",
                "/swagger-ui.html"
        ).permitAll()
                .antMatchers("/**").authenticated()
                .and()
                //头部缓存禁用
                .headers()
                .cacheControl();
    }
}