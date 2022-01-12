package com.ihave.support;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/30 上午10:33
 */
public class RkRule {

    private static final String defDivision = "-";

    public static RkRule build(){
        return new RkRule();
    }

    /**
     * abc
     * @param key
     * @return
     */
    public String baseKey(String key){
        return key;
    }

    /**
     * abc-123
     * @param key
     * @param flag
     * @return
     */
    public String flagKey(String key,String flag){
        return key + defDivision + flag;
    }

    /**
     * abc-123
     * @param key
     * @param flag
     * @param division
     * @return
     */
    public String flagKey(String key,String flag,String division){
        return key + division + flag;
    }
}
