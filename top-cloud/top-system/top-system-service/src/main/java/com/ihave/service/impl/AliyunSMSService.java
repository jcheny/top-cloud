package com.ihave.service.impl;

import com.alibaba.alicloud.sms.ISmsService;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.ihave.enums.SMSStrategy;
import com.ihave.enums.RandomType;
import com.ihave.factory.SMSServiceFactory;
import com.ihave.service.ConfigService;
import com.ihave.service.SMSService;
import com.ihave.support.RkRule;
import com.ihave.utils.RandomUtil;
import com.ihave.vo.ConfigVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/5 上午10:31
 */
@Service
@Slf4j
public class AliyunSMSService implements SMSService, InitializingBean {

    /**
     * 短信验证码的类型
     * int：纯数字
     * STRING：纯字符
     * ALL：数字字符混合
     */
    private static final RandomType randomType = RandomType.INT;
    /**
     * 短信验证码的位数
     */
    private static final int count = 4;
    /**
     * 短信redis 的前缀
     */
    public static final String MOBILE_REDIS_PREFIX = "STMOBILE_SMS_KEY";
    /**
     * redis短信计数 的前缀
     */
    public static final String COUNT_REDIS_PREFIX = "STMOBILE_COUNT_KEY";

    /**
     *redis短信计数过期时间
     */
    public static final Long COUNT_MINUTE = 24L;
    /**
     * redis短信计数时间单位
     */
    public static final TimeUnit COUNT_UNIT = TimeUnit.HOURS;
    /**
     * redis短信计数次数
     */
    public static final Integer COUNT =100;

    /**
     * 短信过期时间
     */
    public static final Long MINUTE = 3L;
    /**
     * 过期时间单位
     */
    public static final TimeUnit TIME_UNIT = TimeUnit.MINUTES;

    /**
     * 短信系统配置中的type值
     */
    public static final String TYPE = "SMS";

    /**
     * 短信系统配置中的签名code值
     */
    public static final String CODE = "SIGN";


    @Resource
    private ISmsService smsService;


    @Autowired
    private ConfigService configService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 发送验证码
     * @param mobile
     * @param code 验证码下的什么场景
     * @throws Exception
     */
    @Override
    public void send(String mobile, String code){

        ConfigVo sign = configService.getConfigByKey(TYPE, CODE);
        if(sign == null){
            throw new ApiException("短信签名异常。请联系管理员");
        }
        ConfigVo sms = configService.getConfigByKey(TYPE, code);
        if(sms == null){
            throw new ApiException("没有找到当前的短信模板，请联系管理员");
        }
        // 判断当前手机号短信发送是否达到上限
        String random = RandomUtil.random(count, randomType);
        Boolean aBoolean = redisTemplate.hasKey(RkRule.build().flagKey(COUNT_REDIS_PREFIX + code,mobile));
        if(aBoolean == null || !aBoolean){
            redisTemplate.opsForValue().set(RkRule.build().flagKey(COUNT_REDIS_PREFIX + code,mobile),COUNT,COUNT_MINUTE,COUNT_UNIT);
        }
        if(Integer.parseInt(String.valueOf(redisTemplate.opsForValue().get(RkRule.build().flagKey( COUNT_REDIS_PREFIX + code,mobile))))<1){
            throw new ApiException("短信已发送已达上限");
        }
        // 未达到上限，则发送短信
        redisTemplate.opsForValue().set(RkRule.build().flagKey(code, mobile),random,MINUTE,TIME_UNIT);
        redisTemplate.opsForValue().decrement(RkRule.build().flagKey(COUNT_REDIS_PREFIX + code,mobile));
        log.info("短信验证码为："+ random);
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setPhoneNumbers(mobile);
        sendSmsRequest.setSignName(sign.getValue());
        sendSmsRequest.setTemplateCode(sms.getValue());
        sendSmsRequest.setTemplateParam(constructParam(sms.getDesc(), random));
        try {
            smsService.sendSmsRequest(sendSmsRequest);
        } catch (ClientException e) {
            log.info("发送短信失败");
            e.printStackTrace();
        }
    }


    @Override
    public boolean check(String mobile, String code,String type) {
        Object result = redisTemplate.opsForValue().get(RkRule.build().flagKey(type, mobile));
        if(result == null){
            throw new ApiException("验证码已过期");
        }
        if(result.toString().equals(code)){
            redisTemplate.delete(RkRule.build().flagKey(type, mobile));
            return true;
        }
        throw new ApiException("验证码错误");
    }

    @Override
    public boolean checkClient(String mobile, String code, String type) {
        Object result = redisTemplate.opsForValue().get(RkRule.build().flagKey(type, mobile));
        if(result == null){
            return false;
        }
        if(result.toString().equals(code)){
            redisTemplate.delete(RkRule.build().flagKey(type, mobile));
            return true;
        }
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SMSServiceFactory.addSMSService(SMSStrategy.ALIYUN_CHECK_SMS,this);
    }
}
