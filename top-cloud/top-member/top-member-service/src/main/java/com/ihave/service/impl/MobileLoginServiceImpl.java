package com.ihave.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.ihave.constants.SysConstant;
import com.ihave.dto.JwtToken;
import com.ihave.entity.Member;
import com.ihave.entity.OauthClientDto;
import com.ihave.enums.LoginStrategy;
import com.ihave.factory.LoginServiceFactory;
import com.ihave.fegin.OAuth2FeignClient;
import com.ihave.fegin.OauthClientFegin;
import com.ihave.fegin.SmsFegin;
import com.ihave.service.LoginService;
import com.ihave.service.MemberService;
import com.ihave.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/30 上午10:09
 */
@Service
@Slf4j
public class MobileLoginServiceImpl implements LoginService, InitializingBean {

    public static final String countryCode = "+86";

    public static final String grantType = "direct";

    private static final String PARAM_MOBILE = "mobile";

    private static final String PARAM_CODE = "code";

    private static final String PARAM_TYPE = "type";

    @Resource
    private OAuth2FeignClient oAuth2FeignClient;

    @Value("${spring.application.name}")
    public String serverName;

    @Resource
    private MemberService memberService;

    @Resource
    private OauthClientFegin oauthClientFegin;

    @Resource
    private SmsFegin smsFegin;

    @Override
    public LoginVo login() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        String mobile = requestAttributes.getRequest().getParameter(PARAM_MOBILE);
        String code = requestAttributes.getRequest().getParameter(PARAM_CODE);
        String type = requestAttributes.getRequest().getParameter(PARAM_TYPE);
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(mobile)) {
            throw new ApiException("手机号验证码不能为空");
        }
        boolean smsResult = smsFegin.checkClient(mobile, code, type);//2s
        if (!smsResult) {
            throw new ApiException("验证码错误");
        }
        List<Member> list = memberService.list(new LambdaQueryWrapper<Member>().eq(Member::getMobile, mobile));//2s
        ResponseEntity<JwtToken> token;
        if (CollectionUtils.isEmpty(list)) {
            //用户未注册
            Member member = new Member();
            member.setMobile(mobile);
            member.setCountryCode(countryCode);
            member.setUsername(UUID.randomUUID().toString().replaceAll("-", ""));
            String password = UUID.randomUUID().toString().replaceAll("-", "");
            member.setPassword(password);
            memberService.save(member);
        }
        log.info("远程调用授权服务器开始");
        //2s
        token = oAuth2FeignClient.getToken(mobile, LOGIN_TYPE,grantType, getHeader());
        if (token.getStatusCode() != HttpStatus.OK) {
            throw new ApiException("系统错误");
        }
        //2.开始登录  --> 远程调用授权服务器  -->  解析调用接口 ---> token
        JwtToken body = token.getBody();
        LoginVo loginVo = new LoginVo();
        BeanUtil.copyProperties(body, loginVo);
        //2s
        return loginVo;
    }

    /**
     * 获取客户端
     *
     * @return
     */
    private OauthClientDto getClient() {
        List<OauthClientDto> client = oauthClientFegin.getClient(serverName);
        if (CollectionUtils.isEmpty(client)) {
            throw new ApiException(SysConstant.SYSTEM_ERROR);
        }
        return client.get(0);
    }

    /**
     * 获取头部
     *
     * @return
     */
    private String getHeader() {
        OauthClientDto client = getClient();
        return this.getHeader(client.getClientId(), client.getClientSecret());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LoginServiceFactory.addLoginService(LoginStrategy.MOBILE, this);
    }
}

