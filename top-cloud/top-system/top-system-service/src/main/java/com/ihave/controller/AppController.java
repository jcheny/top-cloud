package com.ihave.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihave.api.R;
import com.ihave.entity.App;
import com.ihave.request.AppRequest;
import com.ihave.service.AppService;
import com.ihave.utils.ParamUtil;
import com.ihave.vo.AppVo;
import com.ihave.wrapper.AppWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Controller
 * @ClassName: AppController
 * @author CHENYU
 * @date 2021-08-16 21:44:51
 */
@Api(tags = "app管理控制器")
@RestController
@RequestMapping("/app")
public class AppController{

	
	@Autowired
	private AppService appService;
	
	
	/**
	 * 页面展示
	 * @param page
	 * @return String
	 * @author CHENYU
	 */
	@ApiOperation(value = "app分页查询", notes = "分页查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "current", value = "当前页数", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "size", value = "每页条数", paramType = "query", dataType = "int")
	})
	@GetMapping("/page")
	@PreAuthorize("hasAuthority('APP_QUERY')")
	public R<IPage<AppVo>> page(@ApiIgnore Page<App> page,String appName)
    {
		Page<App> pageList = appService.page(page, new LambdaQueryWrapper<App>()
				.like(!StringUtils.isEmpty(appName),App::getAppName,appName)
				.orderByDesc(App::getCreateTime)
		);
		return R.data(AppWrapper.build().pageVO(pageList));
    }

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('APP_QUERY')")
	@ApiOperation(value = "app根据id获取", notes = "id")
	public R<App> getOne(@PathVariable(value = "id") Long id) {
		return R.data(appService.getById(id));
	}

	@PostMapping
	@ApiImplicitParams({
			@ApiImplicitParam(name = "appName", value = "app名称", paramType = "query", dataType = "string")
	})
	@ApiOperation(value = "app新增", notes = "实体")
	@PreAuthorize("hasAuthority('APP_SAVE')")
	public R<Object> save(@RequestBody @ApiIgnore AppRequest request) {
		return R.status(appService.save(AppWrapper.build().requestEntity(request)));
	}

	@PutMapping
	@ApiImplicitParams({
			@ApiImplicitParam(name = "appName", value = "app名称", paramType = "query", dataType = "string")
	})
	@ApiOperation(value = "app修改", notes = "实体")
	@PreAuthorize("hasAuthority('APP_EDIT')")
	public R<Object> update(@RequestBody @ApiIgnore AppRequest request) {
		return R.status(appService.updateById(AppWrapper.build().requestEntity(request)));
	}

	@DeleteMapping
	@ApiImplicitParam(name = "ids", value = "ids", paramType = "query", dataType = "string")
	@ApiOperation(value = "app删除", notes = "ids")
	@PreAuthorize("hasAuthority('APP_DELETE')")
	public R<Object> delete(@ApiIgnore @RequestBody List<Long> ids) {
		ParamUtil.checkNull(ids,"id不能为空");
		return R.status(appService.removeByIds(ids));
	}
}
