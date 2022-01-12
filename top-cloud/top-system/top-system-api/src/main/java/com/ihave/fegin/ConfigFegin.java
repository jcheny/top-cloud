package com.ihave.fegin;

import com.ihave.config.auth.OAuth2FeignConfig;
import com.ihave.constants.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/2 上午10:03
 */
@FeignClient(value = AppConstant.APPLICATION_SYSTEM_NAME,
        configuration = OAuth2FeignConfig.class,
        path = "/config",
        contextId = "config")
public interface ConfigFegin {

    @GetMapping("/getConfig")
    String getConfig(@RequestParam("type") String type,
                     @RequestParam("code") String code);

}
