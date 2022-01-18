package com.ihave.config.auth;

import com.ihave.config.auth.exception.AuthExceptionEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author: chen
 * @Date: 2021/4/16 9:44 AM
 * @Version 1.0
 */
@EnableResourceServer
@Configuration
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private AuthExceptionEntryPoint authExceptionEntryPoint;

    @Value("${spring.application.name}")
    private String applicationName;

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
                "/register",
                "/swagger-resources/configuration/ui",//用来获取支持的动作
                "/swagger-resources",//用来获取api-docs的URI
                "/swagger-resources/configuration/security",//安全选项
                "/webjars/**",
                "/doc.html",
                "/favicon.ico",
                "/swagger-ui.html",
                "/client/getClient",
                "/config/getConfig",
                "/versionUpgrade/check",
                "/sms/**",
                "/api/**",
                "/social/oauth/**",
                "/actuator/**"
        ).permitAll()
                .antMatchers("/**").authenticated()
                .and()
                //头部缓存禁用
                .headers()
                .cacheControl()
        ;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore())
                .authenticationEntryPoint(authExceptionEntryPoint)
                .resourceId(applicationName);
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        ClassPathResource classPathResource = new ClassPathResource("coinexchange.pub");
        String publicKey = null;
        try {
            //使用了FileCopyUtils 将加载到的公钥 --->   字节数组
            byte[] bytes = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
            publicKey = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.info("读取公钥失败");
        }
        //设置验证的公钥
        jwtAccessTokenConverter.setVerifierKey(publicKey);
        return jwtAccessTokenConverter;
    }

}
