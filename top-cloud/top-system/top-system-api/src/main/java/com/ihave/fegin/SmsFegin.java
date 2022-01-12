package com.ihave.fegin;

import com.ihave.config.auth.OAuth2FeignConfig;
import com.ihave.constants.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/30 上午10:55
 */
@FeignClient(value = AppConstant.APPLICATION_SYSTEM_NAME,
        configuration = OAuth2FeignConfig.class,
        path = "/sms",
        contextId = "sms")
public interface SmsFegin {

    @GetMapping("/checkClient")
    boolean checkClient(@RequestParam("mobile") String mobile,
                        @RequestParam("code") String code,
                        @RequestParam("type") String type);


}
