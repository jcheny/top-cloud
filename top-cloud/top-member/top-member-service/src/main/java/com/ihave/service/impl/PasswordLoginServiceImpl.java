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
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/30 上午10:09
 */
@Service
@Slf4j
public class PasswordLoginServiceImpl implements LoginService, InitializingBean {

    public static final String grantType = "password";
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_PASSWORD = "password";

    @Resource
    private OAuth2FeignClient oAuth2FeignClient;

    @Value("${spring.application.name}")
    public String serverName;

    @Resource
    private OauthClientFegin oauthClientFegin;

    @Override
    public LoginVo login() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        String username = requestAttributes.getRequest().getParameter(PARAM_USERNAME);
        String password = requestAttributes.getRequest().getParameter(PARAM_PASSWORD);
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            throw new ApiException("用户名或密码不能为空");
        }
        //2.开始登录  --> 远程调用授权服务器  -->  解析调用接口 ---> token
        List<OauthClientDto> client = oauthClientFegin.getClient(serverName);
        if(CollectionUtils.isEmpty(client)){
            throw new ApiException("系统异常");
        }
        OauthClientDto oauthClientDto = client.get(0);
        String header = this.getHeader(oauthClientDto.getClientId(),oauthClientDto.getClientSecret());
        ResponseEntity<JwtToken> token = new ResponseEntity<JwtToken>(HttpStatus.BAD_REQUEST);
        try{
            token = oAuth2FeignClient.getToken(username, password, LOGIN_TYPE,grantType, header);
        }catch (Exception e){
            throw new ApiException("用户名或密码错误");
        }
        JwtToken body = token.getBody();
        LoginVo loginVo = new LoginVo();
        BeanUtil.copyProperties(body,loginVo);
        return loginVo;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        LoginServiceFactory.addLoginService(LoginStrategy.PASSWORD,this);
    }

}
