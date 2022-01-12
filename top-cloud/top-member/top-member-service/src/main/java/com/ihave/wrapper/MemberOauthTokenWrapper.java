package com.ihave.wrapper;

import cn.hutool.core.bean.BeanUtil;
import com.ihave.entity.MemberOauthToken;
import com.ihave.request.MemberOauthTokenRequest;
import com.ihave.support.BaseEntityWrapper;
import com.ihave.vo.MemberOauthTokenVo;

public class MemberOauthTokenWrapper extends BaseEntityWrapper<MemberOauthToken, MemberOauthTokenVo> {


	public static MemberOauthTokenWrapper build(){
		return new MemberOauthTokenWrapper();
	}

	@Override
	public MemberOauthTokenVo entityVO(MemberOauthToken entity) {
			MemberOauthTokenVo vo = new MemberOauthTokenVo();
		BeanUtil.copyProperties(entity,vo);
		return vo;
	}

	public MemberOauthToken requestEntity(MemberOauthTokenRequest request) {
		MemberOauthToken entity = new MemberOauthToken();
		BeanUtil.copyProperties(request,entity);
		return entity;
	}
}