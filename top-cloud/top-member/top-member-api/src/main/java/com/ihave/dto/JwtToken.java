package com.ihave.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 来解析请求授权服务的类
 *
 * @Author: chen
 * @Date: 2021/4/19 10:51 AM
 * @Version 1.0
 */
@Data
public class JwtToken implements Serializable {
    /**
     * access_token
     */
    @JsonProperty("access_token")
    private String accessToken;

    /**
     * token类型
     */
    @JsonProperty("token_type")
    private String tokenType;

    /**
     * refresh_token
     */
    @JsonProperty("refresh_token")
    private String refreshToken;

    /**
     * 过期时间
     */
    @JsonProperty("expires_in")
    private Long expiresIn;


    /**
     * token的范围
     */
    private String scope;

    /**
     * 颁发的凭证
     */
    private String jti;
}
