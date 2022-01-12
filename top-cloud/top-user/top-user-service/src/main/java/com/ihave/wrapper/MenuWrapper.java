package com.ihave.wrapper;

import cn.hutool.core.bean.BeanUtil;
import com.ihave.entity.Menu;
import com.ihave.node.ForestNodeMerger;
import com.ihave.request.MenuRequest;
import com.ihave.support.BaseEntityWrapper;
import com.ihave.vo.MenuVo;

import java.util.List;
import java.util.stream.Collectors;

public class MenuWrapper extends BaseEntityWrapper<Menu, MenuVo> {


	public static MenuWrapper build(){
		return new MenuWrapper();
	}

	@Override
	public MenuVo entityVO(Menu entity) {
			MenuVo vo = new MenuVo();
		BeanUtil.copyProperties(entity,vo);
		return vo;
	}

	public Menu requestEntity(MenuRequest request) {
		Menu entity = new Menu();
		BeanUtil.copyProperties(request,entity);
		return entity;
	}

	public List<MenuVo> listNodeVO(List<Menu> list) {
		List<MenuVo> collect = list.stream().map(menu -> com.ihave.utils.BeanUtil.copy(menu, MenuVo.class)).collect(Collectors.toList());
		return ForestNodeMerger.merge(collect);
	}
}