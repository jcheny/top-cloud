package com.ihave.fegin;

import com.ihave.config.auth.OAuth2FeignConfig;
import com.ihave.constants.AppConstant;
import com.ihave.dto.RoleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/6 下午3:30
 */
@FeignClient(value = AppConstant.APPLICATION_USER_NAME,
        configuration = OAuth2FeignConfig.class,
        path = "/role",
        contextId = "role")
public interface RoleFegin {

    @GetMapping("/codeToId")
    RoleDto nameToDto(@RequestParam String roleCode);

}
