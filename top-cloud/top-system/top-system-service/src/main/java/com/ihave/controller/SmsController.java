package com.ihave.controller;

import com.ihave.api.R;
import com.ihave.enums.SMSStrategy;
import com.ihave.factory.SMSServiceFactory;
import com.ihave.fegin.SmsFegin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/5 上午10:04
 */
@RestController
@Api(tags = "短信控制器")
@RequestMapping("/sms")
public class SmsController implements SmsFegin {


    @GetMapping("/send")
    @ApiOperation(value = "发送验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "手机号", name = "mobile", paramType = "query", dataType = "String", example = "10086"),
            @ApiImplicitParam(value = "业务名称", name = "code", paramType = "query", dataType = "String", example = "SMS_LOGIN_MESSAGE")
    })
    public void sms(String mobile, String code) throws Exception {
        SMSServiceFactory.getSMSService(SMSStrategy.ALIYUN_CHECK_SMS).send(mobile,code);
    }

    @GetMapping("/check")
    @ApiOperation(value = "校验验证码是否正确")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "手机号", name = "mobile", paramType = "query", dataType = "String", example = "10086"),
            @ApiImplicitParam(value = "业务类型", name = "type", paramType = "query", dataType = "String", example = "SMS_LOGIN_MESSAGE"),
            @ApiImplicitParam(value = "验证码", name = "code", paramType = "query", dataType = "String", example = "2563")
    })
    public R<Object> check(String mobile, String code,String type){
        return R.data(SMSServiceFactory.getSMSService(SMSStrategy.ALIYUN_CHECK_SMS).check(mobile,code,type));
    }

    @ApiIgnore
    @Override
    public boolean checkClient(String mobile, String code, String type) {
        return SMSServiceFactory.getSMSService(SMSStrategy.ALIYUN_CHECK_SMS).checkClient(mobile,code,type);
    }
}
