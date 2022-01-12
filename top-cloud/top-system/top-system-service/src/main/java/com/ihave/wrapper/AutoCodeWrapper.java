package com.ihave.wrapper;

import cn.hutool.core.bean.BeanUtil;
import com.ihave.entity.AutoCode;
import com.ihave.request.AutoCodeRequest;
import com.ihave.support.BaseEntityWrapper;
import com.ihave.vo.AutoCodeVo;

public class AutoCodeWrapper extends BaseEntityWrapper<AutoCode, AutoCodeVo> {


	public static AutoCodeWrapper build(){
		return new AutoCodeWrapper();
	}

	@Override
	public AutoCodeVo entityVO(AutoCode entity) {
			AutoCodeVo vo = new AutoCodeVo();
		BeanUtil.copyProperties(entity,vo);
		return vo;
	}

	public AutoCode requestEntity(AutoCodeRequest request) {
		AutoCode entity = new AutoCode();
		BeanUtil.copyProperties(request,entity);
		return entity;
	}
}