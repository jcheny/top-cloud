package com.ihave.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/26 下午6:47
 */
@Data
public class AuthUserToken implements Serializable {

    private String accessToken;

    private Integer expireIn;

    private String refreshToken;

    private String scope;

    private String tokenType;


}
