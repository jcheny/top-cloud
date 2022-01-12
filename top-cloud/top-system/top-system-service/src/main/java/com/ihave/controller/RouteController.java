package com.ihave.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihave.api.R;
import com.ihave.constants.SysConstant;
import com.ihave.entity.Route;
import com.ihave.service.RouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/7/29 上午11:13
 */
@RestController
@RequestMapping("/route")
@Api(tags = "网关路由控制器")
public class RouteController {

    @Autowired
    private RouteService routeService;


    /**
     * 查询路由信息
     * @param parameters
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询路由信息")
    @PreAuthorize("hasAuthority('ROUTER_QUERY')")
    public R<IPage<Route>> list(@RequestParam Map<String,Object> parameters){
        // 分页查询
        Object pageNum = Optional.ofNullable(parameters.get("pageNum")).orElse(SysConstant.DEFAULT_PAGE_NUM);
        Object pageSize = Optional.ofNullable(parameters.get("pageSize")).orElse(SysConstant.DEFAULT_PAGE_SIZE);
        Page<Route> page = new Page<>(Integer.parseInt(pageNum.toString()),Integer.parseInt(pageSize.toString()));

        String routeId = Optional.ofNullable(parameters.get("routeId")).orElse("").toString();
        String routeUri = Optional.ofNullable(parameters.get("routeUri")).orElse("").toString();
        String predicates = Optional.ofNullable(parameters.get("predicates")).orElse("").toString();
        Page<Route> routePage = routeService.page(page, new LambdaQueryWrapper<Route>()
            .like(Route::getRouteId,routeId)
            .like(Route::getRouteUri,routeUri)
            .like(Route::getPredicates,predicates));

        return R.data(routePage);
    }

    /**
     * 添加路由
     * {
     * 	"filters": "1",
     * 	"predicates": "user",
     * 	"routeId": "stmobile-user",
     * 	"routeOrder": 0,
     * }
     * @param route
     * @return
     */
    @PostMapping
    @ApiOperation(value="添加路由信息")
    @PreAuthorize("hasAuthority('ROUTER_SAVE')")
    public R<Object> save(@RequestBody Route route){
        return R.data(routeService.saveRoute(route));
    }

    /**
     * 修改路由
     * @param route
     * @return
     */
    @PutMapping
    @ApiOperation(value="修改路由信息")
    @PreAuthorize("hasAuthority('ROUTER_EDIT')")
    public R<Object> update(@RequestBody Route route){
        return R.data(routeService.updateRoute(route));
    }

    /**
     * 删除路由
     * @param routeIds
     * @return
     */
    @DeleteMapping
    @ApiOperation(value="删除路由信息")
    @PreAuthorize("hasAuthority('ROUTER_DELETE')")
    @ApiImplicitParam(name = "routeIds",value = "routeIds")
    public R<Object> delete(@RequestBody List<String> routeIds){
        return R.status(routeService.deleteRouteByIds(routeIds));
    }

}
