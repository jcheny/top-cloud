package com.ihave.config.provider;

import com.ihave.config.token.DirectAuthenticationToken;
import lombok.Builder;
import org.apache.commons.lang.NullArgumentException;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

/**
 * 自定义手机模式的提供者
 * 判断token类型是否为DirectAuthenticationToken,如果是则会使用此provider
 *
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/23 上午9:51
 */
@Builder
public class DirectAuthenticationProvider implements AuthenticationProvider, MessageSourceAware {

    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    private static final Integer AUTH_SUCCESS_CODE = 2000;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

    private final UserDetailsService userDetailsService;


    /**
     * 业务处理的方法
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //未认证之前是手机号 认证之后是客户信息
        // 若采用手机登录，则为手机号；若采用第三方登录，则为第三方应用的username
        String user = (String) authentication.getPrincipal();
        if (StringUtils.isEmpty(user)) {
            throw new BadCredentialsException("用户标识不能为空");
        }

        //查询用户对象构建token
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(user);
        } catch (UsernameNotFoundException var6) {
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "查询用户信息失败!"));
        }
        check(userDetails);
        DirectAuthenticationToken authenticationToken = new DirectAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationToken.setDetails(authenticationToken.getDetails());
        return authenticationToken;
    }

    /**
     * 账号禁用、锁定、超时校验
     *
     * @param user
     */
    private void check(UserDetails user) {
        if (user == null) {
            throw new NullArgumentException(this.messages.getMessage("未查询到用户", "未查询到用户"));
        }
        if (!user.isAccountNonLocked()) {
            throw new LockedException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked", "User account is locked"));
        } else if (!user.isEnabled()) {
            throw new DisabledException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled", "User is disabled"));
        } else if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.expired", "User account has expired"));
        }
    }

    /**
     * 判断是否为DirectAuthenticationToken类型  如果是 直接调用 切断过滤器链
     * 如果不是 继续查找
     *
     * @param authentication
     * @return boolean
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return DirectAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
