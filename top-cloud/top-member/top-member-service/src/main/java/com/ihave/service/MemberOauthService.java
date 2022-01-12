package com.ihave.service;

import com.ihave.entity.MemberOauth;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ihave.request.MemberOauthRequest;

/**
 * 用户第三方认证表 MemberOauthService
 * @Title: MemberOauthService.java
 * @Package com.sensemobile.service
 * @author CHENYU_自动生成
 * @date 2021-10-14 11:12:30
 **/
public interface MemberOauthService extends IService<MemberOauth>{

    Object binding(MemberOauthRequest request);
}
