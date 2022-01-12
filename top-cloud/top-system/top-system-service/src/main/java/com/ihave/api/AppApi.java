package com.ihave.api;

import com.ihave.service.AppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/12/2 上午10:54
 */
@Api(tags = "app_api")
@RestController
@RequestMapping("/api")
public class AppApi {

    @Autowired
    private AppService appService;

    @GetMapping(value = "/agreement/{appName}/{type}",produces =  MediaType.TEXT_HTML_VALUE)
    @ApiOperation(value = "根据app名称获取权限", notes = "id")
    public String getAgreement(@PathVariable(value = "appName") String appName,
                             @PathVariable(value = "type") String type) {
        String meta = "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">";
        return meta + appService.getAgreement(appName, type);
    }


}
