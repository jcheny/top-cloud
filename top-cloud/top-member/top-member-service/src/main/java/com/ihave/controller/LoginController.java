package com.ihave.controller;

import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.ihave.api.R;
import com.ihave.factory.LoginServiceFactory;
import com.ihave.request.MemberRequest;
import com.ihave.service.LoginService;
import com.ihave.service.MemberService;
import com.ihave.utils.ParamUtil;
import com.ihave.vo.LoginVo;
import com.ihave.wrapper.MemberWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/30 上午9:46
 */
@RestController
@Api(tags = "登陆相关控制器")
public class LoginController {


    @Autowired
    private MemberService memberService;


    @PostMapping("/login")
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

    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public R<Object> register(MemberRequest request){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(request.getPassword());
        request.setPassword(encode);
        return R.data(memberService.save(MemberWrapper.build().requestEntity(request)));
    }

}
