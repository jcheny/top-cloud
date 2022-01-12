package com.ihave.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/26 下午3:28
 */
@JsonSerialize(using = UserOAuth2ExceptionSerializer.class)
public class UserOAuth2Exception extends OAuth2Exception {
    private Integer status = 400;

    public UserOAuth2Exception(String message, Throwable t) {
        super(message, t);
        status = ((OAuth2Exception) t).getHttpErrorCode();
    }

    public UserOAuth2Exception(String message) {
        super(message);
    }

    @Override
    public int getHttpErrorCode() {
        return status;
    }

}
