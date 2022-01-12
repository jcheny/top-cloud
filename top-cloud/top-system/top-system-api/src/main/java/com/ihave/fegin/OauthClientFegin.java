package com.ihave.fegin;

import com.ihave.config.auth.OAuth2FeignConfig;
import com.ihave.constants.AppConstant;
import com.ihave.entity.OauthClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/2 上午10:23
 */
@FeignClient(value = AppConstant.APPLICATION_SYSTEM_NAME,
        configuration = OAuth2FeignConfig.class,
        path = "/client",
        contextId = "client")
public interface OauthClientFegin {

    /**
     *  根据服务名获取客户端密钥
     * @param serverName
     * @return
     */
    @GetMapping("/getClient")
    List<OauthClientDto> getClient(@RequestParam("serverName") String serverName);

}
