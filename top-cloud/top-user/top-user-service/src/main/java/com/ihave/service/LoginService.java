package com.ihave.service;

import com.ihave.vo.LoginVo;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/30 上午10:07
 */
public interface LoginService {

    String Basic = "Basic";

    String BASIC_CLIENT = "top-api";

    String BASIC_SECRET = "top-secret";

    String LOGIN_TYPE = "admin";

    /**
     * <p>不同的grantType对应不同的实现类</p>
     *
     * <pre>
     * grantType = "mobile"              = MobileLoginServiceImpl
     * grantType = "password"            = PasswordLoginServiceImpl
     * grantType = "refresh"             = RefreshTokenServiceImpl
     * grantType = "social"              = SocialLoginServiceImpl
     * </pre>
     * @return <code>true</code> 返回授权之后的token数据
     */
    LoginVo login();

    /**
     * @param APP_KEY app key 客户端key
     * @param SECRET_KEY app secret 客户端secret
     * @return 获取加密的数据头
     */
    default String getHeader(String APP_KEY,String SECRET_KEY) {
        if(StringUtils.isEmpty(APP_KEY)){
            APP_KEY = BASIC_CLIENT;
        }
        if(StringUtils.isEmpty(SECRET_KEY)){
            SECRET_KEY = BASIC_SECRET;
        }
        String auth = APP_KEY + ":" + SECRET_KEY;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
        return Basic + " " + new String(encodedAuth);
    }

}
