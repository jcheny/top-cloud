package com.ihave.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.ihave.dto.AuthUser;
import com.ihave.dto.JwtToken;
import com.ihave.entity.Member;
import com.ihave.entity.MemberOauth;
import com.ihave.entity.MemberOauthToken;
import com.ihave.entity.OauthClientDto;
import com.ihave.enums.LoginStrategy;
import com.ihave.factory.LoginServiceFactory;
import com.ihave.fegin.OAuth2FeignClient;
import com.ihave.fegin.OauthClientFegin;
import com.ihave.fegin.SocialFegin;
import com.ihave.service.LoginService;
import com.ihave.service.MemberOauthService;
import com.ihave.service.MemberOauthTokenService;
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
public class SocialLoginServiceImpl implements LoginService, InitializingBean {

    private final Snowflake snowflake = new Snowflake(1,1);

    public static final String grantType = "direct";

    private static final String PARAM_CODE = "code";

    private static final String PARAM_STATE = "state";

    private static final String PARAM_SOURCE = "source";

    @Resource
    private OAuth2FeignClient oAuth2FeignClient;

    @Value("${spring.application.name}")
    public String serverName;

    @Resource
    private OauthClientFegin oauthClientFegin;

    @Resource
    private SocialFegin socialFegin;

    @Resource
    private MemberOauthService memberOauthService;

    @Resource
    private MemberOauthTokenService memberOauthTokenService;

    @Resource
    private MemberService memberService;

    @Override
    public LoginVo login() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        String state = requestAttributes.getRequest().getParameter(PARAM_STATE);
        String code = requestAttributes.getRequest().getParameter(PARAM_CODE);
        String source = requestAttributes.getRequest().getParameter(PARAM_SOURCE);
        if(StringUtils.isEmpty(code) || StringUtils.isEmpty(state) || StringUtils.isEmpty(source)){
            throw new ApiException("第三方登录数据不能为空");
        }
        //请求系统服务
        ResponseEntity<String> result = socialFegin.checkSocial(source, state, code);
        if(result.getStatusCode()!= HttpStatus.OK){
            throw new ApiException("第三方登录错误");
        }
        AuthUser authUser = JSON.parseObject(result.getBody(),AuthUser.class);
        if(authUser == null){
            throw new ApiException("系统异常");
        }
        //检查用户
        MemberOauth memberOauth = memberOauthService.getOne(new LambdaQueryWrapper<MemberOauth>().eq(MemberOauth::getUuid, authUser.getUuid()));
        if(memberOauth == null){
            //保存用户
            Member member = new Member();
            member.setUsername(authUser.getUsername());
            String password = UUID.randomUUID().toString().replaceAll("-", "");
            member.setPassword(password);
            memberService.save(member);
            //保存第三方用户信息
            memberOauth = new MemberOauth();
            BeanUtil.copyProperties(authUser,memberOauth);
            memberOauth.setMemberId(member.getId());
            memberOauthService.save(memberOauth);
        }
        //保存第三方登陆token
        MemberOauthToken memberOauthToken = new MemberOauthToken();
        BeanUtil.copyProperties(authUser.getToken(),memberOauthToken);
        memberOauthToken.setMemberOauthId(memberOauth.getId());
        memberOauthTokenService.save(memberOauthToken);

        //2.开始登录  --> 远程调用授权服务器  -->  解析调用接口 ---> token
        List<OauthClientDto> client = oauthClientFegin.getClient(serverName);
        if(CollectionUtils.isEmpty(client)){
            throw new ApiException("系统异常");
        }
        OauthClientDto oauthClientDto = client.get(0);
        String header = this.getHeader(oauthClientDto.getClientId(),oauthClientDto.getClientSecret());

        ResponseEntity<JwtToken> token = oAuth2FeignClient.getToken(memberOauth.getUsername(),LOGIN_TYPE, grantType,header );

        if (token.getStatusCode() != HttpStatus.OK) {
            throw new ApiException("系统异常");
        }
        JwtToken body = token.getBody();
        LoginVo loginVo = new LoginVo();
        BeanUtil.copyProperties(body,loginVo);
        return loginVo;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LoginServiceFactory.addLoginService(LoginStrategy.SOCIAL,this);

    }
}
