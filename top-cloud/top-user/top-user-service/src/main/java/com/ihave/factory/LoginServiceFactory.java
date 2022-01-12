package com.ihave.factory;

import com.ihave.enums.LoginStrategy;
import com.ihave.service.LoginService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/30 上午10:10
 */
public class LoginServiceFactory {

    private static final Map<LoginStrategy, LoginService> loginServiceMap = new HashMap<>() ;

    /**
     * 给我们的策略工厂里面添加一个交易的实现类型
     */
    public static  void addLoginService(LoginStrategy loginStrategy,LoginService loginService){
        loginServiceMap.put(loginStrategy ,loginService ) ;
    }


    /**
     * 使用策略的名称获取具体的实现类
     * @return
     */
    public static LoginService getLoginService(LoginStrategy loginStrategy){
        return loginServiceMap.get(loginStrategy) ;
    }
    public static LoginService getLoginService(String grantType){
        LoginStrategy loginStrategy = LoginStrategy.getLoginStrategy(grantType);
        return getLoginService(loginStrategy) ;
    }

}
