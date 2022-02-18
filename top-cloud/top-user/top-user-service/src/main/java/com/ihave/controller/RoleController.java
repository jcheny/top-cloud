package com.ihave.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.ihave.api.R;
import com.ihave.dto.RoleDto;
import com.ihave.entity.Role;
import com.ihave.fegin.RoleFegin;
import com.ihave.request.RoleAuthorizeRequest;
import com.ihave.request.RoleRequest;
import com.ihave.service.RoleService;
import com.ihave.utils.BeanUtil;
import com.ihave.utils.ParamUtil;
import com.ihave.vo.RoleVo;
import com.ihave.wrapper.RoleWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/29 上午10:58
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色控制器")
public class RoleController implements RoleFegin {

    @Autowired
    private RoleService roleService;

    @GetMapping
    @ApiOperation(value = "查询角色列表", notes = "角色名称查询")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "角色名称", name = "name", paramType = "query", dataType = "string", example = "admin"),
            @ApiImplicitParam(name = "current", value = "当前页数", paramType = "query", dataType = "int", example = "1"),
            @ApiImplicitParam(name = "size", value = "每页条数", paramType = "query", dataType = "int", example = "10")
    })
    @PreAuthorize("hasAuthority('ROLE_QUERY')")
    public R<List<RoleVo>> list(String name,String code) {
        List<Role> rolePage = roleService.list( new LambdaQueryWrapper<Role>()
                .like(StringUtils.isNoneBlank(name),Role::getName, name)
                .like(StringUtils.isNoneBlank(code),Role::getCode,code)
                .orderByDesc(Role::getCreateTime)
        );
        return R.data(RoleWrapper.build().listVO(rolePage));
    }


    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(value = "角色名称", name = "name", paramType = "query", dataType = "string", example = "管理员"),
            @ApiImplicitParam(name = "code", value = "代码", paramType = "query", dataType = "int", example = "ADMIN"),
            @ApiImplicitParam(name = "description", value = "描述", paramType = "query", dataType = "int", example = "拥有一切权限")
    })
    @ApiOperation(value = "角色新增", notes = "角色实体")
    @PreAuthorize("hasAuthority('ROLE_SAVE')")
    public R<Object> save(@RequestBody @ApiIgnore RoleRequest request) {
        ParamUtil.checkNull(request.getCode(),"code不能为空");
        ParamUtil.checkNull(request.getName(),"name不能为空");
        return R.status(roleService.save(RoleWrapper.build().requestEntity(request)));
    }

    @PutMapping
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id", name = "id", paramType = "query", dataType = "long", example = "123132132"),
            @ApiImplicitParam(value = "角色名称", name = "name", paramType = "query", dataType = "string", example = "管理员"),
            @ApiImplicitParam(name = "code", value = "代码", paramType = "query", dataType = "string", example = "ADMIN"),
            @ApiImplicitParam(name = "description", value = "描述", paramType = "query", dataType = "string", example = "拥有一切权限")
    })
    @ApiOperation(value = "角色修改", notes = "角色实体")
    @PreAuthorize("hasAuthority('ROLE_EDIT')")
    public R<Object> update(@RequestBody @ApiIgnore RoleRequest request) {
        ParamUtil.checkNull(request.getId(),"id不能为空");
        return R.status(roleService.updateById(RoleWrapper.build().requestEntity(request)));
    }

    @DeleteMapping
    @ApiImplicitParam(name = "ids", value = "角色ids", paramType = "query", dataType = "Long", example = "123123")
    @ApiOperation(value = "角色删除", notes = "角色ids")
    @PreAuthorize("hasAuthority('ROLE_DELETE')")
    public R<Object> delete(@ApiIgnore @RequestBody List<Long> ids) {
        ParamUtil.checkNull(ids,"id不能为空");
        return R.status(roleService.removeByIds(ids));
    }

    @PostMapping("authorize")
    @PreAuthorize("hasAuthority('ROLE_AUTHORIZE')")
    @ApiOperation(value = "角色授权")
    public R authorize(@RequestBody RoleAuthorizeRequest request){
        return R.status(roleService.authorize(request));
    }

    @PostMapping("authorizeRun")
    @PreAuthorize("hasAuthority('ROLE_AUTHORIZE')")
    @ApiOperation(value = "角色授权")
    public R authorizeRun(@RequestBody RoleAuthorizeRequest request){
        return R.status(roleService.authorizeRun(request));
    }


    @ApiIgnore
    @Override
    public RoleDto nameToDto(String roleCode) {
        List<Role> list = roleService.list(new LambdaQueryWrapper<Role>().eq(Role::getCode, roleCode));
        if(CollectionUtils.isEmpty(list)){
            throw new ApiException("没有找到该角色code");
        }
        return BeanUtil.copy(list.get(0), RoleDto.class);
    }
}
