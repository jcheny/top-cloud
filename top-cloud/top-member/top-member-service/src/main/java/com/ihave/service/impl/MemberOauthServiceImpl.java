package com.ihave.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.ihave.dto.AuthUser;
import com.ihave.entity.MemberOauthToken;
import com.ihave.fegin.SocialFegin;
import com.ihave.request.MemberOauthRequest;
import com.ihave.service.MemberOauthTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihave.mapper.MemberOauthMapper;
import com.ihave.entity.MemberOauth;
import com.ihave.service.MemberOauthService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户第三方认证表 MemberOauthService
 * @Title: MemberOauthService.java
 * @Package com.sensemobile.service
 * @author CHENYU_自动生成
 * @date 2021-10-14 11:12:30
 **/
@Service
public class MemberOauthServiceImpl extends ServiceImpl<MemberOauthMapper, MemberOauth> implements MemberOauthService{

    @Resource
    private SocialFegin socialFegin;

    @Resource
    private MemberOauthTokenService userOauthTokenService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object binding(MemberOauthRequest request) {
        ResponseEntity<String> stringResponseEntity = socialFegin.checkSocial(request.getSource(), request.getState(), request.getCode());
        if(stringResponseEntity.getStatusCode() != HttpStatus.OK){
            throw new ApiException("绑定异常");
        }
        AuthUser authUser = JSON.parseObject(stringResponseEntity.getBody(),AuthUser.class);
        if(authUser == null){
            throw new ApiException("系统异常");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //设置用户关系
        long userId = Long.parseLong(authentication.getPrincipal().toString());
        MemberOauth userOauth = new MemberOauth();
        BeanUtil.copyProperties(authUser,userOauth);
        userOauth.setMemberId(userId);
        this.save(userOauth);

        //保存第三方登陆token
        MemberOauthToken userOauthToken = new MemberOauthToken();
        BeanUtil.copyProperties(authUser.getToken(),userOauthToken);
        userOauthToken.setMemberOauthId(userOauth.getId());
        userOauthTokenService.save(userOauthToken);
        return true;
    }
}
