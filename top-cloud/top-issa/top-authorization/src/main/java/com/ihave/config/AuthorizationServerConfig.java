package com.ihave.config;

import com.ihave.config.client.MyJdbcClientDetailsService;
import com.ihave.config.granter.TokenGranterExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;

/**
 * @Author: chen
 * @Date: 2021/4/9 2:22 PM
 * @Version 1.0
 */
@EnableAuthorizationServer // 开启授权服务器的功能
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    //密码加密方式
    @Autowired
    private PasswordEncoder passwordEncoder;

    //验证管理器
    @Autowired
    private AuthenticationManager authenticationManager;

    //登录业务类
    @Qualifier("userServiceDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private WebResponseExceptionTranslator<OAuth2Exception> webResponseExceptionTranslator;

    @Autowired
    private DataSource dataSource;

    /**
     * 添加第三方的客户端
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(myJdbcClientDetailsService());
    }

    @Bean
    public MyJdbcClientDetailsService myJdbcClientDetailsService() {
        MyJdbcClientDetailsService myJdbcClientDetailsService = new MyJdbcClientDetailsService(dataSource);
        myJdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
        return myJdbcClientDetailsService;
    }

    /**
     * 配置验证管理器，UserdetailService
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenGranter tokenGranter = TokenGranterExt.getTokenGranter(authenticationManager, endpoints);
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                // tokenStore 来存储我们的token jwt 存储token
                .tokenStore(jwtTokenStore())
                .tokenGranter(tokenGranter)
                //token怎么产生  把登录的实体转化成json
                .tokenEnhancer(jwtAccessTokenConverter())
                .exceptionTranslator(webResponseExceptionTranslator)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        ;

        super.configure(endpoints);
    }

    //配置一个JwtTokenStore
    private TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    //把私钥加载到TokenConverter
    private JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        // 加载我们的私钥
        ClassPathResource classPathResource = new ClassPathResource("coinexchange.jks");
        //需要一个密钥对
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource, "coinexchange".toCharArray());
        //得到一个密钥对
        tokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("coinexchange", "coinexchange".toCharArray()));
        return tokenConverter;
    }

}