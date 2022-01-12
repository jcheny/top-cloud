package com.ihave.factory;

import com.ihave.enums.SMSStrategy;
import com.ihave.service.SMSService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/30 上午10:10
 */
public class SMSServiceFactory {

    private static Map<SMSStrategy, SMSService> smsServiceMap = new HashMap<>() ;

    /**
     * 给我们的策略工厂里面添加短信类型
     */
    public static  void addSMSService(SMSStrategy smsStrategy,SMSService smsService){
        smsServiceMap.put(smsStrategy ,smsService ) ;
    }


    /**
     * 使用策略的名称获取具体的实现类
     * @return
     */
    public static SMSService getSMSService(SMSStrategy smsStrategy){
        return smsServiceMap.get(smsStrategy) ;
    }

    public static SMSService getSMSService(String business){
        return getSMSService( SMSStrategy.getSMSStrategy(business)) ;
    }

}
