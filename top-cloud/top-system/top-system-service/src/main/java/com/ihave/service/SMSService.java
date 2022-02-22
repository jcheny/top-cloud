package com.ihave.service;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/5 上午10:21
 */
public interface SMSService {

    String regex = "\\{([^}]*)\\}";

    /**
     *
     * @param mobile 手机号
     * @param code 场景code
     * @throws Exception
     */
    void send(String mobile, String code) throws Exception;


    default Matcher matcher(String desc){
        Pattern compile = Pattern.compile(regex);
        return compile.matcher(desc);
    }

    default String constructParam(String desc,String... param) {
        if(param.length == 0){
            return desc;
        }
        Matcher matcher = matcher(desc);
        List<String> jsonKey = new LinkedList<>();
        Map<String, Object> json = new HashMap<>();
        while(matcher.find()){
            String group = matcher.group().replaceAll("\\{", "").replaceAll("}", "");
            jsonKey.add(group);
        }
        for(int i = 0;i< jsonKey.size(); i++){
            json.put(jsonKey.get(i),param[i] == null ? "null" : param[i]);
        }
        return constructParam(json);
    }

    default String constructParam(Map<String,Object> map) {
        return JSON.toJSONString(map);
    }

    default String constructParam(Object object) {
        return JSON.toJSONString(object);
    }


    boolean check(String mobile, String code,String type);

    boolean checkClient(String mobile, String code,String type);
}
