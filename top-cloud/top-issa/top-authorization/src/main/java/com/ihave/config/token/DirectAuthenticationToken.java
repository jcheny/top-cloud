package com.ihave.config.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 继承自AbstractAuthenticationToken 用于自定义的token
 *
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/23 上午9:49
 */
public class DirectAuthenticationToken extends AbstractAuthenticationToken {

    /**
     * 认证之前  存的是第三方信息
     * 认证之后 存的是用户信息
     */
    private final Object principal;

    private Object credentials;

    public DirectAuthenticationToken(Object principal) {
        super(null);
        this.principal = principal;
        //设置没有认证
        setAuthenticated(false);
    }

    public DirectAuthenticationToken(Object principal,
                                     Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        //设置已经认证
        super.setAuthenticated(true);
    }


    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }


    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "认证失败");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }
}
