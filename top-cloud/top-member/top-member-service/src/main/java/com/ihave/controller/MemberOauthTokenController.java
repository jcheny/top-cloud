package com.ihave.controller;

import com.ihave.api.R;
import com.ihave.entity.MemberOauthToken;
import com.ihave.request.MemberOauthTokenRequest;
import com.ihave.service.MemberOauthTokenService;
import com.ihave.utils.ParamUtil;
import com.ihave.vo.MemberOauthTokenVo;
import com.ihave.wrapper.MemberOauthTokenWrapper;
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
 * 第三方客户端token表Controller
 * @ClassName: MemberOauthTokenController
 * @author CHENYU
 * @date 2021-10-14 11:12:39
 */
@Api(tags = "第三方客户端token表")
@RestController
@RequestMapping("/MemberOauthToken")
public class MemberOauthTokenController{

	
	@Autowired
	private MemberOauthTokenService memberOauthTokenService;
	
	
	/**
	 * 第三方客户端token表页面展示
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
    public R<IPage<MemberOauthTokenVo>> page(@ApiIgnore Page<MemberOauthToken> page)
    {
		Page<MemberOauthToken> pageList = memberOauthTokenService.page(page, new LambdaQueryWrapper<MemberOauthToken>()
				.orderByDesc(MemberOauthToken::getCreateTime)
		);
		return R.data(MemberOauthTokenWrapper.build().pageVO(pageList));
    }

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取", notes = "id")
	public R<MemberOauthToken> getOne(@PathVariable(value = "id") Long id) {
		return R.data(memberOauthTokenService.getById(id));
	}

	@PostMapping
	@ApiOperation(value = "新增", notes = "实体")
	public R save(@RequestBody MemberOauthTokenRequest request) {
		return R.status(memberOauthTokenService.save(MemberOauthTokenWrapper.build().requestEntity(request)));
	}

	@PutMapping
	@ApiOperation(value = "修改", notes = "实体")
	public R update(@RequestBody MemberOauthTokenRequest request) {
		return R.status(memberOauthTokenService.updateById(MemberOauthTokenWrapper.build().requestEntity(request)));
	}

	@DeleteMapping
	@ApiImplicitParam(name = "ids", value = "ids", paramType = "query", dataType = "Long")
	@ApiOperation(value = "删除", notes = "ids")
	public R delete(@ApiIgnore @RequestBody List<Long> ids) {
		ParamUtil.checkNull(ids,"id不能为空");
		return R.status(memberOauthTokenService.removeByIds(ids));
	}
}
