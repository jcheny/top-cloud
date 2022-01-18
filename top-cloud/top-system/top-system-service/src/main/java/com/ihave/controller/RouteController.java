package com.ihave.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihave.api.R;
import com.ihave.entity.Route;
import com.ihave.service.RouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/7/29 上午11:13
 */
@RestController
@RequestMapping("/route")
@Api(tags = "网关路由控制器")
public class RouteController {

    private static final String ROUTE_PREFIX = "lb://";

    @Autowired
    private RouteService routeService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询路由数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "routeId", value = "路由id"),
            @ApiImplicitParam(name = "current", value = "当前页数", paramType = "query", dataType = "int", example = "1"),
            @ApiImplicitParam(name = "size", value = "每页条数", paramType = "query", dataType = "int", example = "10")
    })
    @PreAuthorize("hasAuthority('ROUTE_QUERY')")
    public R<IPage<Route>> page(@ApiIgnore Page<Route> page, String routeId){
        Page<Route> routePage = routeService.page(page, new LambdaQueryWrapper<Route>()
                .like(StringUtils.isNotBlank(routeId), Route::getRouteId, routeId)
                .orderByDesc(Route::getCreateTime));
        return R.data(routePage);
    }

    @PostMapping
    @ApiOperation(value = "保存路由数据")
    @PreAuthorize("hasAuthority('ROUTE_SAVE')")
    public R<Object> save(@RequestBody Route route){
        route.setRouteUri(ROUTE_PREFIX + route.getRouteUri());
        return R.status(routeService.save(route));
    }

    @PutMapping
    @ApiOperation(value = "更新路由数据")
    @PreAuthorize("hasAuthority('ROUTE_EDIT')")
    public R<Object> update(@RequestBody Route route){
        return R.status(routeService.updateById(route));
    }

    @DeleteMapping
    @ApiOperation(value = "删除路由数据")
    @PreAuthorize("hasAuthority('ROUTE_DELETE')")
    public R<Object> delete(@ApiIgnore @RequestBody List<Long> ids){
        return R.status(routeService.removeByIds(ids));
    }

    @GetMapping("/push")
    @ApiOperation(value = "发布路由")
    @PreAuthorize("hasAuthority('ROUTE_PUSH')")
    public R<Object> push(Long id){
        return R.status(routeService.pushRouter(id));
    }

    @GetMapping("/outline")
    @ApiOperation(value = "发布路由")
    @PreAuthorize("hasAuthority('ROUTE_OUTLINE')")
    public R<Object> outline(Long id){
        return R.status(routeService.outlineRouter(id));
    }
}
