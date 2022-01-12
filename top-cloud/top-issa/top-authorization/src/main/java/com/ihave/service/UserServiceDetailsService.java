package com.ihave.service;

import com.ihave.constant.LoginConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Author: chen
 * @Date: 2021/4/9 2:26 PM
 * @Version 1.0
 */

@Service("userServiceDetailsService")
public class UserServiceDetailsService extends BaseUserService implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        UserDetails userDetails = null;
        try {
            String grantType = requestAttributes.getRequest().getParameter(LoginConstant.GRANT_TYPE); // refresh_token 进行纠正
            if (LoginConstant.REFRESH_TYPE.equals(grantType.toUpperCase())) {
                username = super.adjustUsername(username, jdbcTemplate);
            }
            String loginType = requestAttributes.getRequest().getParameter(LoginConstant.LOGIN_TYPE); // refresh_token 进行纠正

            if (StringUtils.isBlank(loginType)) {
                throw new UsernameNotFoundException("无法识别登录");
            }

            if (loginType.equals(LoginConstant.LOGIN_TYPE_ADMIN)) {
                userDetails = super.getUser(username, jdbcTemplate, LoginConstant.QUERY_ADMIN_SQL);
            }
            if (loginType.equals(LoginConstant.LOGIN_TYPE_MEMBER)) {
                userDetails = super.getMember(username, jdbcTemplate, LoginConstant.QUERY_MEMBER_SQL);
            }

        } catch (IncorrectResultSizeDataAccessException e) { // 我们的用户不存在
            throw new UsernameNotFoundException("帐号" + username + "不存在");
        }

        return userDetails;
    }


}

