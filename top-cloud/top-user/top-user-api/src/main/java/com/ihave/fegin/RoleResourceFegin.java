package com.ihave.fegin;

import com.ihave.config.auth.OAuth2FeignConfig;
import com.ihave.constants.AppConstant;
import com.ihave.dto.RoleResourceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/3 下午4:06
 */
@FeignClient(value = AppConstant.APPLICATION_USER_NAME,
        configuration = OAuth2FeignConfig.class,
        path = "/role-res",
        contextId = "role-res")
public interface RoleResourceFegin {

    @GetMapping("/roleAll")
    List<RoleResourceDto> roleAll();

    @PostMapping("/saveRoleResource")
    Boolean saveRoleResource(@RequestParam Long roleId,@RequestParam Long resId );

}
