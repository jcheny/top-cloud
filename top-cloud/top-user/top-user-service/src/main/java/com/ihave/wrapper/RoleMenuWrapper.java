package com.ihave.wrapper;

import cn.hutool.core.bean.BeanUtil;
import com.ihave.entity.RoleMenu;
import com.ihave.request.RoleMenuRequest;
import com.ihave.support.BaseEntityWrapper;
import com.ihave.vo.RoleMenuVo;

public class RoleMenuWrapper extends BaseEntityWrapper<RoleMenu, RoleMenuVo> {


	public static RoleMenuWrapper build(){
		return new RoleMenuWrapper();
	}

	@Override
	public RoleMenuVo entityVO(RoleMenu entity) {
			RoleMenuVo vo = new RoleMenuVo();
		BeanUtil.copyProperties(entity,vo);
		return vo;
	}

	public RoleMenu requestEntity(RoleMenuRequest request) {
		RoleMenu entity = new RoleMenu();
		BeanUtil.copyProperties(request,entity);
		return entity;
	}
}