package com.ihave.fegin;

import com.ihave.config.auth.OAuth2FeignConfig;
import com.ihave.constants.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/30 上午11:27
 */
@FeignClient(value = AppConstant.APPLICATION_SYSTEM_NAME,
        configuration = OAuth2FeignConfig.class,
        path = "/social",
        contextId = "social")
public interface SocialFegin {

    @PostMapping("/oauth/user")
    ResponseEntity<String> checkSocial(@RequestParam("source") String source,
                                       @RequestParam("state")String state,
                                       @RequestParam("code")String code);


}
