package com.ihave.wrapper;

import cn.hutool.core.bean.BeanUtil;
import com.ihave.entity.Member;
import com.ihave.request.MemberRequest;
import com.ihave.support.BaseEntityWrapper;
import com.ihave.vo.MemberVo;

public class MemberWrapper extends BaseEntityWrapper<Member, MemberVo> {


	public static MemberWrapper build(){
		return new MemberWrapper();
	}

	@Override
	public MemberVo entityVO(Member entity) {
			MemberVo vo = new MemberVo();
		BeanUtil.copyProperties(entity,vo);
		return vo;
	}

	public Member requestEntity(MemberRequest request) {
		Member entity = new Member();
		BeanUtil.copyProperties(request,entity);
		return entity;
	}
}