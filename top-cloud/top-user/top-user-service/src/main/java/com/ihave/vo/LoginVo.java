package com.ihave.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/30 上午10:04
 */
@Data
public class LoginVo implements Serializable {

    private String accessToken;

    private String refreshToken;

    private String tokenType;

    private String authType;

}
