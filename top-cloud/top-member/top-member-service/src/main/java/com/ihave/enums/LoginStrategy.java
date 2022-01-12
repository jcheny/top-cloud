package com.ihave.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/30 上午10:11
 */
@AllArgsConstructor
@Getter
public enum LoginStrategy {

    PASSWORD("password"),
    MOBILE("mobile"),
    SOCIAL("social"),
    REFRESH("refresh");

    private final String value;

    public static LoginStrategy getLoginStrategy(String value){
        LoginStrategy[] values = LoginStrategy.values();
        for (LoginStrategy v : values) {
            if(v.getValue().equals(value)){
                return v ;
            }
        }
        return null ;
    }

}
