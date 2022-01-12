package com.ihave.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ihave.api.R;
import com.ihave.entity.Menu;
import com.ihave.request.MenuRequest;
import com.ihave.service.MenuService;
import com.ihave.utils.ParamUtil;
import com.ihave.vo.MenuVo;
import com.ihave.vo.RoleHasMenuVo;
import com.ihave.wrapper.MenuWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 系统菜单Controller
 * @ClassName: MenuController
 * @author CHENYU
 * @date 2021-09-07 10:09:21
 */
@Api(tags = "系统菜单")
@RestController
@RequestMapping("/menu")
public class MenuController{

	@Autowired
	private MenuService menuService;

	@PreAuthorize("hasAuthority('MENU_QUERY')")
	@ApiOperation(value = "系统菜单查询(树结构)", notes = "查询")
	@GetMapping("/list")
	public R<List<MenuVo>> list()
	{
		List<Menu> pageList = menuService.list( new LambdaQueryWrapper<Menu>()
				.orderByDesc(Menu::getSort)
		);
		return R.data(MenuWrapper.build().listNodeVO(pageList));
	}

	/**
	 * 系统菜单页面展示
	 * @return String
	 * @author CHENYU
	 */
	@PreAuthorize("hasAuthority('MENU_QUERY')")
	@ApiOperation(value = "系统菜单查询", notes = "(菜单页面)分页查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "类型", paramType = "query", dataType = "int", example = "1"),
	})
	@GetMapping("/menu")
    public R<List<Menu>> menu(Integer type)
    {
		List<Menu> pageList = menuService.list( new LambdaQueryWrapper<Menu>()
				.eq(type != null,Menu::getType,type)
				.orderByDesc(Menu::getSort)
		);
		return R.data(pageList);
    }

	@PreAuthorize("hasAuthority('ROLE_AUTHORIZE')")
	@ApiOperation(value = "查询角色拥有那些权限，那些没有", notes = "（角色页面）用于角色设置时查询使用")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "类型", paramType = "query", dataType = "int", example = "1"),
			@ApiImplicitParam(name = "roleId", value = "角色id", paramType = "query", dataType = "int", example = "1"),
	})
	@GetMapping("/menuAndUserTree")
	public R<RoleHasMenuVo> menuAndUserTree(Long roleId,Integer type)
	{
		return R.data(menuService.menuAndUserTree(roleId,type));
	}

	@ApiOperation(value = "解析用户的角色信息", notes = "（登录）加载按钮使用")
	@GetMapping("/authorize")
	public R<Object> authorize()
	{
		return R.data(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
	}

	@ApiOperation(value = "查询(树结构)", notes = "(登录)查询")
	@GetMapping("/user/list")
	public R<List<MenuVo>> userList(){
		List<Menu> pageList = menuService.userListTree();
		return R.data(MenuWrapper.build().listNodeVO(pageList));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('MENU_QUERY')")
	@ApiOperation(value = "系统菜单根据id获取", notes = "id")
	public R<Menu> getOne(@PathVariable(value = "id") Long id) {
		return R.data(menuService.getById(id));
	}

	@PostMapping
	@PreAuthorize("hasAuthority('MENU_SAVE')")
	@ApiOperation(value = "系统菜单新增", notes = "实体")
	public R save(@RequestBody MenuRequest request) {
		return R.data(menuService.saveMenu(request));
	}

	@PostMapping("/batch")
	@PreAuthorize("hasAuthority('MENU_SAVE')")
	@ApiOperation(value = "批量系统菜单新增", notes = "实体")
	public R batchSave(@RequestBody List<Menu> menu) {
		menuService.saveBatch(menu);
		return R.data(menu);
	}

	@PutMapping
	@PreAuthorize("hasAuthority('MENU_EDIT')")
	@ApiOperation(value = "系统菜单修改", notes = "实体")
	public R update(@RequestBody MenuRequest request) {
		return R.status(menuService.updateById(MenuWrapper.build().requestEntity(request)));
	}

	@DeleteMapping
	@PreAuthorize("hasAuthority('MENU_DELETE')")
	@ApiImplicitParam(name = "ids", value = "ids", paramType = "query", dataType = "Long")
	@ApiOperation(value = "系统菜单删除", notes = "ids")
	public R delete(@ApiIgnore @RequestBody List<Long> ids) {
		ParamUtil.checkNull(ids,"id不能为空");
		return R.status(menuService.removeMenu(ids));
	}
}
