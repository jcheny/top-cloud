package com.ihave.controller;

import com.ihave.api.R;
import com.ihave.entity.MemberOauth;
import com.ihave.request.MemberOauthRequest;
import com.ihave.service.MemberOauthService;
import com.ihave.utils.ParamUtil;
import com.ihave.vo.MemberOauthVo;
import com.ihave.wrapper.MemberOauthWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 用户第三方认证表Controller
 * @ClassName: MemberOauthController
 * @author CHENYU
 * @date 2021-10-14 11:12:30
 */
@Api(tags = "用户第三方认证表")
@RestController
@RequestMapping("/memberOauth")
public class MemberOauthController{

	
	@Autowired
	private MemberOauthService memberOauthService;
	
	
	/**
	 * 用户第三方认证表页面展示
	 * @param page
	 * @return String
	 * @author CHENYU
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "current", value = "当前页数", paramType = "query", dataType = "int", example = "1"),
			@ApiImplicitParam(name = "size", value = "每页条数", paramType = "query", dataType = "int", example = "10")
	})
	@GetMapping("/page")
    public R<IPage<MemberOauthVo>> page(@ApiIgnore Page<MemberOauth> page)
    {
		Page<MemberOauth> pageList = memberOauthService.page(page, new LambdaQueryWrapper<MemberOauth>()
				.orderByDesc(MemberOauth::getCreateTime)
		);
		return R.data(MemberOauthWrapper.build().pageVO(pageList));
    }

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取", notes = "id")
	public R<MemberOauth> getOne(@PathVariable(value = "id") Long id) {
		return R.data(memberOauthService.getById(id));
	}


	@PostMapping("/binding")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "code",value = "用户名",paramType = "query",dataType = "string",example = "ednyf78eyrf67asb67fbtas67"),
			@ApiImplicitParam(name = "state",value = "第三方用户id",paramType = "query",dataType = "string",example = "sdufysa8dbfy67stfb76asdf"),
			@ApiImplicitParam(name = "source",value = "系统用户id",paramType = "query",dataType = "string",example = "WEI_XIN")
	})
	@ApiOperation(value = "绑定第三方用户",notes = "来源，code,state")
	public R<Object> binding(@RequestBody @ApiIgnore MemberOauthRequest request){
		return R.data(memberOauthService.binding(request));
	}


	@DeleteMapping
	@ApiImplicitParam(name = "ids", value = "ids", paramType = "query", dataType = "Long")
	@ApiOperation(value = "删除", notes = "ids")
	public R delete(@ApiIgnore @RequestBody List<Long> ids) {
		ParamUtil.checkNull(ids,"id不能为空");
		return R.status(memberOauthService.removeByIds(ids));
	}
}
