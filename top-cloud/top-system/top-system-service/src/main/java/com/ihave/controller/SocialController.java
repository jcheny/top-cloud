package com.ihave.controller;

import com.alibaba.fastjson.JSON;
import com.ihave.factory.SocialProperties;
import com.ihave.fegin.SocialFegin;
import com.ihave.utils.SocialUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/19 下午2:40
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/social")
@EnableConfigurationProperties(value = SocialProperties.class)
@ConditionalOnProperty(value = "social.enabled", havingValue = "true")
@Api(value = "第三方登陆", tags = "第三方登陆端点")
public class SocialController implements SocialFegin {

    private final SocialProperties socialProperties;

    /**
     * 授权完毕跳转
     */
    @ApiOperation(value = "授权完毕跳转",notes = "路径上加上第三方的来源")
    @GetMapping("/oauth/render/{source}")
    public void renderAuth(@PathVariable("source") String source, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = SocialUtil.getAuthRequest(source, socialProperties);
        String authorizeUrl = authRequest.authorize(AuthStateUtils.createState());
        response.sendRedirect(authorizeUrl);
    }

    /**
     * 获取认证信息
     */
    @ApiOperation(value = "获取认证信息")
    @GetMapping("/oauth/callback/{source}")
    public Object login(@PathVariable("source") String source, AuthCallback callback) {
        AuthRequest authRequest = SocialUtil.getAuthRequest(source, socialProperties);
        return authRequest.login(callback);
    }

    /**
     * 撤销授权
     */
    @ApiOperation(value = "撤销授权")
    @GetMapping("/oauth/revoke/{source}/{token}")
    public Object revokeAuth(@PathVariable("source") String source, @PathVariable("token") String token) {
        AuthRequest authRequest = SocialUtil.getAuthRequest(source, socialProperties);
        return authRequest.revoke(AuthToken.builder().accessToken(token).build());
    }

    /**
     * 续期accessToken
     */
    @ApiOperation(value = "续期令牌")
    @RequestMapping(value = "/oauth/refresh/{source}",method = RequestMethod.GET)
    public Object refreshAuth(@PathVariable("source") String source, String token) {
        AuthRequest authRequest = SocialUtil.getAuthRequest(source, socialProperties);
        return authRequest.refresh(AuthToken.builder().refreshToken(token).build());
    }

    @ApiIgnore
    @Override
    public ResponseEntity<String> checkSocial(String source, String state, String code) {
        AuthCallback authCallback = new AuthCallback();
        authCallback.setCode(code);
        authCallback.setState(state);
        AuthRequest authRequest = SocialUtil.getAuthRequest(source, socialProperties);
        AuthResponse login = authRequest.login(authCallback);
        if(login.getCode() != 200){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(JSON.toJSONString(login.getData()), HttpStatus.OK);
    }
}
