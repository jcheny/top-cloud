package com.ihave.wrapper;

import cn.hutool.core.bean.BeanUtil;
import com.ihave.entity.App;
import com.ihave.request.AppRequest;
import com.ihave.support.BaseEntityWrapper;
import com.ihave.vo.AppVo;

public class AppWrapper extends BaseEntityWrapper<App, AppVo> {


	public static AppWrapper build(){
		return new AppWrapper();
	}

	@Override
	public AppVo entityVO(App entity) {
			AppVo vo = new AppVo();
		BeanUtil.copyProperties(entity,vo);
		return vo;
	}

	public App requestEntity(AppRequest request) {
		App entity = new App();
		BeanUtil.copyProperties(request,entity);
		return entity;
	}
}