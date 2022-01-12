package com.ihave.utils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/29 上午11:21
 */
public class ParamUtil {

    public static void checkNull(Object param,String message){
        if(ObjectUtil.isEmpty(param)){
            throw new ApiException(message);
        }
    }
    public static <T> T trim(T t){
        Class<?> aClass = t.getClass();
        String string = JSON.toJSONString(t);
        String trim = string.replaceAll(" ","");
        Object o = JSON.parseObject(trim,aClass);
        return (T)o;
    }

}
