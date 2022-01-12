package com.ihave.wrapper;

import cn.hutool.core.bean.BeanUtil;
import com.ihave.entity.MemberOauth;
import com.ihave.request.MemberOauthRequest;
import com.ihave.support.BaseEntityWrapper;
import com.ihave.vo.MemberOauthVo;

public class MemberOauthWrapper extends BaseEntityWrapper<MemberOauth, MemberOauthVo> {


	public static MemberOauthWrapper build(){
		return new MemberOauthWrapper();
	}

	@Override
	public MemberOauthVo entityVO(MemberOauth entity) {
			MemberOauthVo vo = new MemberOauthVo();
		BeanUtil.copyProperties(entity,vo);
		return vo;
	}

	public MemberOauth requestEntity(MemberOauthRequest request) {
		MemberOauth entity = new MemberOauth();
		BeanUtil.copyProperties(request,entity);
		return entity;
	}
}