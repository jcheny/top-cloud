package com.ihave.wrapper;

import cn.hutool.core.bean.BeanUtil;
import com.ihave.entity.VersionUpgrade;
import com.ihave.request.VersionUpgradeRequest;
import com.ihave.support.BaseEntityWrapper;
import com.ihave.vo.VersionUpgradeVo;

public class VersionUpgradeWrapper extends BaseEntityWrapper<VersionUpgrade, VersionUpgradeVo> {

	public static VersionUpgradeWrapper build(){
		return new VersionUpgradeWrapper();
	}

	@Override
	public VersionUpgradeVo entityVO(VersionUpgrade entity) {
			VersionUpgradeVo vo = new VersionUpgradeVo();
		BeanUtil.copyProperties(entity,vo);
		return vo;
	}

	public VersionUpgrade requestEntity(VersionUpgradeRequest request) {
		VersionUpgrade entity = new VersionUpgrade();
		BeanUtil.copyProperties(request,entity);
		return entity;
	}
}