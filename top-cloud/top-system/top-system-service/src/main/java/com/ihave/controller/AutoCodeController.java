package com.ihave.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihave.api.R;
import com.ihave.constants.AppConstant;
import com.ihave.entity.AutoCode;
import com.ihave.request.AutoCodeRequest;
import com.ihave.service.AutoCodeService;
import com.ihave.utils.ParamUtil;
import com.ihave.vo.AutoCodeVo;
import com.ihave.wrapper.AutoCodeWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Controller
 * @ClassName: AutoCodeController
 * @author CHENYU
 * @date 2022-01-12 10:49:34
 */
@Api(tags = "自动代码控制器")
@RestController
@RequestMapping("/autoCode")
public class AutoCodeController{

	
	@Autowired
	private AutoCodeService autoCodeService;

	@Value("${spring.profiles.active}")
	private String active;
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
    public R<IPage<AutoCodeVo>> page(@ApiIgnore Page<AutoCode> page,String tableName )
    {
		Page<AutoCode> pageList = autoCodeService.page(page, new LambdaQueryWrapper<AutoCode>()
						.like(StringUtils.isNotBlank(tableName),AutoCode::getTableName,tableName)
						.orderByDesc(AutoCode::getCreateTime)
		);
		return R.data(AutoCodeWrapper.build().pageVO(pageList));
    }

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取", notes = "id")
	public R<AutoCode> getOne(@PathVariable(value = "id") Long id) {
		return R.data(autoCodeService.getById(id));
	}

	@GetMapping("/check/{id}")
	@ApiOperation(value = "检查该条记录数据")
	public R<Object> checkData(@PathVariable(value = "id") Long id){
		return R.data(autoCodeService.checkData(id));
	}

	@GetMapping("/auto/{id}")
	@ApiOperation(value = "该条记录生成代码")
	public R<Object> autoData(@PathVariable(value = "id") Long id){
		if(!active.equals(AppConstant.DEV_CODE)){
			throw new ApiException("非本地环境不可使用");
		}
		autoCodeService.autoData(id);
		return R.status(true);
	}

	@PostMapping
	@ApiOperation(value = "新增", notes = "实体")
	public R<Object> save(@RequestBody AutoCodeRequest request) {
		return R.status(autoCodeService.save(AutoCodeWrapper.build().requestEntity(request)));
	}

	@PutMapping
	@ApiOperation(value = "修改", notes = "实体")
	public R<Object> update(@RequestBody AutoCodeRequest request) {
		return R.status(autoCodeService.updateById(AutoCodeWrapper.build().requestEntity(request)));
	}

	@DeleteMapping
	@ApiImplicitParam(name = "ids", value = "ids", paramType = "query", dataType = "Long")
	@ApiOperation(value = "删除", notes = "ids")
	public R<Object> delete(@ApiIgnore @RequestBody List<Long> ids) {
		ParamUtil.checkNull(ids,"id不能为空");
		return R.status(autoCodeService.removeByIds(ids));
	}
}
