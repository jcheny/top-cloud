package com.ihave.utils;

import org.springframework.beans.BeanUtils;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/3 下午5:07
 */
public class BeanUtil extends BeanUtils {

    public static <T> T copy(Object source, Class<T> clazz){
        T to = newInstance(clazz);
        copyProperties(source,to);
        return to;
    }

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<?> clazz) {
        return (T) instantiateClass(clazz);
    }

}
