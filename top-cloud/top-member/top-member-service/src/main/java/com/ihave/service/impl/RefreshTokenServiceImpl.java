package com.ihave.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.ihave.dto.JwtToken;
import com.ihave.entity.OauthClientDto;
import com.ihave.enums.LoginStrategy;
import com.ihave.factory.LoginServiceFactory;
import com.ihave.fegin.OAuth2FeignClient;
import com.ihave.fegin.OauthClientFegin;
import com.ihave.service.LoginService;
import com.ihave.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/26 上午11:16
 */
@Service
@Slf4j
public class RefreshTokenServiceImpl implements LoginService, InitializingBean {

    private static final String grantType = "refresh_token";

    @Resource
    private OauthClientFegin oauthClientFegin;

    @Value("${spring.application.name}")
    public String serverName;

    @Resource
    private OAuth2FeignClient oAuth2FeignClient;

    @Override
    public LoginVo login() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        String refresh_token = requestAttributes.getRequest().getHeader(grantType);
        if(StringUtils.isEmpty(refresh_token)){
            throw new ApiException("刷新令牌为空");
        }

        List<OauthClientDto> client = oauthClientFegin.getClient(serverName);
        if(CollectionUtils.isEmpty(client)){
            throw new ApiException("系统异常");
        }
        OauthClientDto oauthClientDto = client.get(0);
        ResponseEntity<JwtToken> token = oAuth2FeignClient.refreshToken(LOGIN_TYPE,grantType,refresh_token , this.getHeader(oauthClientDto.getClientId(),oauthClientDto.getClientSecret()));

        JwtToken body = token.getBody();
        LoginVo loginVo = new LoginVo();
        BeanUtil.copyProperties(body,loginVo);
        return loginVo;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LoginServiceFactory.addLoginService(LoginStrategy.REFRESH,this);
    }
}
