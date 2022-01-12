package com.ihave.controller;

import com.ihave.api.R;
import com.ihave.entity.Member;
import com.ihave.request.MemberRequest;
import com.ihave.service.MemberService;
import com.ihave.utils.ParamUtil;
import com.ihave.vo.MemberVo;
import com.ihave.wrapper.MemberWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * Controller
 * @ClassName: MemberController
 * @author CHENYU
 * @date 2021-10-14 11:12:06
 */
@Api(tags = "")
@RestController
@RequestMapping("/member")
public class MemberController{

	
	@Autowired
	private MemberService memberService;
	
	
	/**
	 * 页面展示
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
    public R<IPage<MemberVo>> page(@ApiIgnore Page<Member> page,String username,String mobile)
    {
		Page<Member> pageList = memberService.page(page, new LambdaQueryWrapper<Member>()
				.like(StringUtils.isNotBlank(username),Member::getUsername,username)
				.like(StringUtils.isNotBlank(mobile),Member::getMobile,mobile)
				.orderByDesc(Member::getCreateTime)
		);
		return R.data(MemberWrapper.build().pageVO(pageList));
    }

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取", notes = "id")
	public R<Member> getOne(@PathVariable(value = "id") Long id) {
		return R.data(memberService.getById(id));
	}

	@PostMapping
	@ApiOperation(value = "新增", notes = "实体")
	public R save(@RequestBody MemberRequest request) {
		return R.status(memberService.save(MemberWrapper.build().requestEntity(request)));
	}

	@PutMapping
	@ApiOperation(value = "修改", notes = "实体")
	public R update(@RequestBody MemberRequest request) {
		return R.status(memberService.updateById(MemberWrapper.build().requestEntity(request)));
	}

	@DeleteMapping
	@ApiImplicitParam(name = "ids", value = "ids", paramType = "query", dataType = "Long")
	@ApiOperation(value = "删除", notes = "ids")
	public R delete(@ApiIgnore @RequestBody List<Long> ids) {
		ParamUtil.checkNull(ids,"id不能为空");
		return R.status(memberService.removeByIds(ids));
	}
}
