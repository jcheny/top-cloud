package com.ihave.utils;

import com.ihave.factory.SocialProperties;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.request.*;

import java.util.Objects;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/19 下午2:36
 */
public class SocialUtil {
    public SocialUtil() {
    }

    /**
     *
     * @param source 来源
     * @param socialProperties 属性
     * @return 一个授权请求
     */
    public static AuthRequest getAuthRequest(String source, SocialProperties socialProperties) {
        AuthDefaultSource authSource = (AuthDefaultSource) Objects.requireNonNull(AuthDefaultSource.valueOf(source.toUpperCase()));
        AuthConfig authConfig = (AuthConfig)socialProperties.getOauth().get(authSource);
        if (authConfig == null) {
            throw new AuthException("未获取到有效的Auth配置");
        } else {
            AuthRequest authRequest = null;
            switch(authSource) {
                case GITHUB:
                    authRequest = new AuthGithubRequest(authConfig);
                    break;
                case GITEE:
                    authRequest = new AuthGiteeRequest(authConfig);
                    break;
                case OSCHINA:
                    authRequest = new AuthOschinaRequest(authConfig);
                    break;
                case QQ:
                    authRequest = new AuthQqRequest(authConfig);
                    break;
                case WECHAT_OPEN:
                    authRequest = new AuthWeChatOpenRequest(authConfig);
                    break;
                case WECHAT_ENTERPRISE:
                    authRequest = new AuthWeChatEnterpriseRequest(authConfig);
                    break;
                case WECHAT_MP:
                    authRequest = new AuthWeChatMpRequest(authConfig);
                    break;
                case DINGTALK:
                    authRequest = new AuthDingTalkRequest(authConfig);
                    break;
                case ALIPAY:
                    authRequest = new AuthAlipayRequest(authConfig);
                    break;
                case BAIDU:
                    authRequest = new AuthBaiduRequest(authConfig);
                    break;
                case WEIBO:
                    authRequest = new AuthWeiboRequest(authConfig);
                    break;
                case CODING:
                    authRequest = new AuthCodingRequest(authConfig);
                    break;
                case CSDN:
                    authRequest = new AuthCsdnRequest(authConfig);
                    break;
                case TAOBAO:
                    authRequest = new AuthTaobaoRequest(authConfig);
                    break;
                case GOOGLE:
                    authRequest = new AuthGoogleRequest(authConfig);
                    break;
                case FACEBOOK:
                    authRequest = new AuthFacebookRequest(authConfig);
                    break;
                case DOUYIN:
                    authRequest = new AuthDouyinRequest(authConfig);
                    break;
                case LINKEDIN:
                    authRequest = new AuthLinkedinRequest(authConfig);
                    break;
                case MICROSOFT:
                    authRequest = new AuthMicrosoftRequest(authConfig);
                    break;
                case MI:
                    authRequest = new AuthMiRequest(authConfig);
                    break;
                case TOUTIAO:
                    authRequest = new AuthToutiaoRequest(authConfig);
                    break;
                case TEAMBITION:
                    authRequest = new AuthTeambitionRequest(authConfig);
                    break;
                case PINTEREST:
                    authRequest = new AuthPinterestRequest(authConfig);
                    break;
                case RENREN:
                    authRequest = new AuthRenrenRequest(authConfig);
                    break;
                case STACK_OVERFLOW:
                    authRequest = new AuthStackOverflowRequest(authConfig);
                    break;
                case HUAWEI:
                    authRequest = new AuthHuaweiRequest(authConfig);
                    break;
                case KUJIALE:
                    authRequest = new AuthKujialeRequest(authConfig);
                    break;
                case GITLAB:
                    authRequest = new AuthGitlabRequest(authConfig);
                    break;
                case MEITUAN:
                    authRequest = new AuthMeituanRequest(authConfig);
                    break;
                case ELEME:
                    authRequest = new AuthElemeRequest(authConfig);
                    break;
                case TWITTER:
                    authRequest = new AuthTwitterRequest(authConfig);
            }

            if (null == authRequest) {
                throw new AuthException("未获取到有效的Auth配置");
            } else {
                return (AuthRequest)authRequest;
            }
        }
    }
}
