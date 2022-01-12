package com.ihave.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/5 上午10:25
 */
@Getter
@AllArgsConstructor
public enum SMSStrategy {

    ALIYUN_CHECK_SMS("ALIYUN_CHECK_SMS");

    private final String code;

    public static SMSStrategy getSMSStrategy(String value){
        SMSStrategy[] values = SMSStrategy.values();
        for (SMSStrategy v : values) {
            if(v.getCode().equals(value)){
                return v ;
            }
        }
        return null ;
    }
}
