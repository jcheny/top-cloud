package com.ihave.fegin;

import com.ihave.config.auth.OAuth2FeignConfig;
import com.ihave.constants.AppConstant;
import com.ihave.dto.JwtToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/30 上午10:55
 */
@FeignClient(value = AppConstant.APPLICATION_AUTH_NAME,
        contextId = "oauth",
        configuration = OAuth2FeignConfig.class)
public interface OAuth2FeignClient {

    @PostMapping("/oauth/token")
    ResponseEntity<JwtToken> getToken(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("loginType") String loginType,
            @RequestParam("grant_type") String grantType,
            @RequestHeader("Authorization") String basicToken
    );


    @PostMapping("/oauth/token")
    ResponseEntity<JwtToken> getToken(
            @RequestParam("user") String user,
            @RequestParam("loginType") String loginType,
            @RequestParam("grant_type") String grantType,
            @RequestHeader("Authorization") String basicToken
    );

    @PostMapping("/oauth/token")
    ResponseEntity<JwtToken> refreshToken(
            @RequestParam("loginType") String loginType,
            @RequestParam("grant_type") String grantType,
            @RequestParam("refresh_token") String refresh_token,
            @RequestHeader("Authorization") String header);
}
