package com.ihave.controller;

import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.ihave.api.R;
import com.ihave.factory.LoginServiceFactory;
import com.ihave.service.LoginService;
import com.ihave.utils.ParamUtil;
import com.ihave.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/30 上午9:46
 */
@RestController
@RequestMapping("/login")
@Api(tags = "登陆相关控制器")
public class LoginController {

    @PostMapping
    @ApiOperation(value = "登录")
    public R<LoginVo> login(@RequestParam("grantType") String grantType){
        ParamUtil.checkNull(grantType,"登陆类型不能null");
        LoginService loginService = LoginServiceFactory.getLoginService(grantType);
        if(loginService == null){
            throw new ApiException("登录类型不支持");
        }
        LoginVo login = loginService.login();

        return R.data(login);
    }

}
