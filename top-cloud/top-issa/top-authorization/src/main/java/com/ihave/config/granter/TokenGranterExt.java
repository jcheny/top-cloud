package com.ihave.config.granter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TokenGranter 扩展 将自定义的grant_type类型添加到oauth2中
 * 使用方法:
 * 在configure(AuthorizationServerEndpointsConfigurer endpoints)中:
 * //获取自定义tokenGranter
 * TokenGranter tokenGranter = TokenGranterExt.getTokenGranter(authenticationManager, endpoints, baseRedis, userClient, socialProperties);
 * endpoints.tokenGranter(tokenGranter);
 *
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/23 上午9:57
 */
public class TokenGranterExt {
    public static TokenGranter getTokenGranter(final AuthenticationManager authenticationManager,
                                               final AuthorizationServerEndpointsConfigurer endpointsConfigurer
    ) {

        //  默认tokenGranter集合 security 自带的
        List<TokenGranter> granters = new ArrayList<>(Collections.singletonList(endpointsConfigurer.getTokenGranter()));
        //添加验证方式
        granters.add(new DirectTokenGranter(authenticationManager, endpointsConfigurer.getTokenServices(), endpointsConfigurer.getClientDetailsService(), endpointsConfigurer.getOAuth2RequestFactory()));
        // ....
        return new CompositeTokenGranter(granters);
    }
}
