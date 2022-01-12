package com.ihave.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihave.api.R;
import com.ihave.entity.VersionUpgrade;
import com.ihave.request.VersionUpgradeRequest;
import com.ihave.service.VersionUpgradeService;
import com.ihave.utils.ParamUtil;
import com.ihave.vo.VersionUpgradeVo;
import com.ihave.wrapper.VersionUpgradeWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Controller
 * @ClassName: VersionUpgradeController
 * @author CHENYU
 * @date 2021-08-11 10:07:00
 */
@Api(tags = "apk升级管理")
@RestController
@RequestMapping("/versionUpgrade")
public class VersionUpgradeController{

	
	@Autowired
	private VersionUpgradeService versionUpgradeService;

	@GetMapping("/check")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "app版本", name = "version", paramType = "query", dataType = "string", example = "1.2"),
			@ApiImplicitParam(value = "appID", name = "appId", paramType = "query", dataType = "string", example = "153165165")
	})
	@ApiOperation(value = "检查更新",notes = "传入版本号,如果有最新的apk则会返回数据，否则data就为null")
	public R<VersionUpgradeVo> check(@ApiIgnore String version,Long appId){
		return R.data(VersionUpgradeWrapper.build().entityVO(versionUpgradeService.check(version,appId)));
	}
	
	/**
	 * 页面展示
	 * @param page
	 * @return String
	 * @author CHENYU
	 */
	@ApiOperation(value = "apk升级管理分页查询", notes = "分页查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "appId", value = "appId", paramType = "query", dataType = "long"),
			@ApiImplicitParam(name = "current", value = "当前页数", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "size", value = "每页条数", paramType = "query", dataType = "int")
	})
	@GetMapping("/page")
	@PreAuthorize("hasAuthority('UPGRADE_MANGER')")
    public R<IPage<VersionUpgradeVo>> page(@ApiIgnore Page<VersionUpgrade> page,Long appId)
    {
		Page<VersionUpgrade> pageList = versionUpgradeService.page(page, new LambdaQueryWrapper<VersionUpgrade>()
				.eq(appId != null,VersionUpgrade::getAppId,appId)
				.orderByDesc(VersionUpgrade::getCreateTime)
		);
		return R.data(VersionUpgradeWrapper.build().pageVO(pageList));
    }

	@GetMapping("/{id}")
	@ApiOperation(value = "apk升级管理根据id获取", notes = "id")
	@PreAuthorize("hasAuthority('UPGRADE_MANGER')")
	public R<VersionUpgrade> getOne(@PathVariable(value = "id") Long id) {
		return R.data(versionUpgradeService.getById(id));
	}

	@PostMapping
	@ApiOperation(value = "apk升级管理新增", notes = "实体")
	@PreAuthorize("hasAuthority('UPGRADE_MANGER')")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "appId", name = "appId", paramType = "query", dataType = "String"),
			@ApiImplicitParam(value = "大版本号id", name = "versionId", paramType = "query", dataType = "String"),
			@ApiImplicitParam(value = "版本标识 1.2", name = "versionCode", paramType = "query", dataType = "String"),
			@ApiImplicitParam(value = "是否升级 1升级，0不升级，2强制升级", name = "type", paramType = "query", dataType = "String"),
			@ApiImplicitParam(value = "安装包url", name = "apkUrl", paramType = "query", dataType = "String"),
			@ApiImplicitParam(value = "升级提示", name = "upgradePoint", paramType = "query", dataType = "String"),
			@ApiImplicitParam(value = "小版本号", name = "versionMini", paramType = "query", dataType = "String")
	})
	public R<Object> save(@RequestBody VersionUpgradeRequest request) {
		return R.status(versionUpgradeService.save(VersionUpgradeWrapper.build().requestEntity(request)));
	}

	@PutMapping
	@ApiImplicitParams({
			@ApiImplicitParam(value = "id", name = "id", paramType = "query", dataType = "String"),
			@ApiImplicitParam(value = "大版本号id", name = "versionId", paramType = "query", dataType = "String"),
			@ApiImplicitParam(value = "版本标识 1.2", name = "versionCode", paramType = "query", dataType = "String"),
			@ApiImplicitParam(value = "是否升级 1升级，0不升级，2强制升级", name = "type", paramType = "query", dataType = "String"),
			@ApiImplicitParam(value = "安装包url", name = "apkUrl", paramType = "query", dataType = "String"),
			@ApiImplicitParam(value = "升级提示", name = "upgradePoint", paramType = "query", dataType = "String"),
			@ApiImplicitParam(value = "小版本号", name = "versionMini", paramType = "query", dataType = "String")
	})
	@ApiOperation(value = "apk升级管理修改", notes = "实体")
	@PreAuthorize("hasAuthority('UPGRADE_MANGER')")
	public R<Object> update(@RequestBody VersionUpgradeRequest request) {
		return R.status(versionUpgradeService.updateById(VersionUpgradeWrapper.build().requestEntity(request)));
	}

	@DeleteMapping
	@ApiImplicitParam(name = "ids", value = "ids", paramType = "query", dataType = "Long")
	@ApiOperation(value = "apk升级管理删除", notes = "ids")
	@PreAuthorize("hasAuthority('UPGRADE_MANGER')")
	public R<Object> delete(@ApiIgnore @RequestBody List<Long> ids) {
		ParamUtil.checkNull(ids,"id不能为空");
		return R.status(versionUpgradeService.removeByIds(ids));
	}
}
