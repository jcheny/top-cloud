package com.ihave.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihave.api.R;
import com.ihave.entity.User;
import com.ihave.request.UserRequest;
import com.ihave.service.UserRoleService;
import com.ihave.service.UserService;
import com.ihave.utils.ParamUtil;
import com.ihave.vo.UserVo;
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
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/26 下午4:19
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户控制器")
public class UserController{

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",paramType = "query",dataType = "string",example = "zhangsan"),
            @ApiImplicitParam(name = "mobile",value = "手机号",paramType = "query",dataType = "string",example = "15111111111"),
            @ApiImplicitParam(name = "current", value = "当前页数",paramType = "query",dataType = "int",example = "1"),
            @ApiImplicitParam(name = "size", value = "每页条数",paramType = "query",dataType = "int",example = "10")
    })
    @ApiOperation(value = "用户列表",notes = "用户名，手机号查询,分页查询")
    @PreAuthorize("hasAuthority('USER_QUERY')")
    public R<IPage<UserVo>> list(@ApiIgnore Page<User> page,
                                 String username,
                                 String mobile,
                                 String email,
                                 Integer status,
                                 Long roleId){
        return R.data(userService.pageUser(page,username,mobile,email,status,roleId));
    }

    @GetMapping("/info")
    @ApiOperation(value = "用户INFO",notes = "用户名，手机号查询,分页查询")
    public R<UserVo> userInfo(){
        return R.data(userService.userInfo(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()));
    }

    @PostMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",paramType = "query",dataType = "string",example = "zhangsan"),
            @ApiImplicitParam(name = "password",value = "密码",paramType = "query",dataType = "string",example = "zhangsan"),
            @ApiImplicitParam(name = "mobile",value = "手机号",paramType = "query",dataType = "string",example = "15111111111"),
            @ApiImplicitParam(name = "email",value = "邮箱",paramType = "query",dataType = "string",example = "zhangsan"),
            @ApiImplicitParam(name = "avatar",value = "头像",paramType = "query",dataType = "string",example = "15111111111"),
            @ApiImplicitParam(name = "birthday", value = "生日",paramType = "query",dataType = "string",example = "2021-12-20"),
            @ApiImplicitParam(name = "sex", value = "性别[0:女,1:男]",paramType = "query",dataType = "int",example = "1")
    })
    @ApiOperation(value = "用户新增",notes = "用户实体")
    @PreAuthorize("hasAuthority('USER_SAVE')")
    public R<Object> save(@RequestBody @ApiIgnore UserRequest user){
        return R.data(userService.saveUser(user));
    }

    @PutMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",paramType = "query",dataType = "long",example = "123165"),
            @ApiImplicitParam(name = "username",value = "用户名",paramType = "query",dataType = "string",example = "zhangsan"),
            @ApiImplicitParam(name = "mobile",value = "手机号",paramType = "query",dataType = "string",example = "15111111111"),
            @ApiImplicitParam(name = "email",value = "邮箱",paramType = "query",dataType = "string",example = "zhangsan"),
            @ApiImplicitParam(name = "avatar",value = "头像",paramType = "query",dataType = "string",example = "15111111111"),
            @ApiImplicitParam(name = "birthday", value = "生日",paramType = "query",dataType = "string",example = "2021-12-20"),
            @ApiImplicitParam(name = "sex", value = "性别[0:女,1:男]",paramType = "query",dataType = "int",example = "1")
    })
    @ApiOperation(value = "用户修改",notes = "用户实体")
    @PreAuthorize("hasAuthority('USER_EDIT')")
    public R<Object> update(@RequestBody @ApiIgnore UserRequest user){
        ParamUtil.checkNull(user.getId(),"id不能为空");
        return R.data(userService.updateUser(user));
    }

    @PutMapping("forgetPwd")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code",value = "SMS业务类型",paramType = "query",dataType = "string",example = "SMS_UPDATE_PWD"),
            @ApiImplicitParam(name = "smsCode",value = "短信验证码",paramType = "query",dataType = "string",example = "2541"),
            @ApiImplicitParam(name = "password",value = "密码",paramType = "query",dataType = "string",example = "123456"),
            @ApiImplicitParam(name = "confirmPassword",value = "确认密码",paramType = "query",dataType = "string",example = "123456")
    })
    @ApiOperation(value = "忘记密码",notes = "用户实体")
    public R<Object> forgetPassword(@RequestBody @ApiIgnore UserRequest user){
        ParamUtil.checkNull(user.getPassword(), "密码不能为空");
        ParamUtil.checkNull(user.getConfirmPassword(), "确认密码不能为空");
        ParamUtil.checkNull(user.getSmsCode(), "短信验证码不能为空");
        return R.data(userService.forgetPwd(user));
    }

    @PutMapping("/updatePwd")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password",value = "密码",paramType = "query",dataType = "string",example = "123456"),
            @ApiImplicitParam(name = "oldPassword",value = "旧密码",paramType = "query",dataType = "string",example = "123456"),
            @ApiImplicitParam(name = "confirmPassword",value = "确认密码",paramType = "query",dataType = "string",example = "123456")
    })
    @ApiOperation(value = "修改密码",notes = "用户实体")
    public R<Object> updatePassword(@RequestBody @ApiIgnore UserRequest user){
        ParamUtil.checkNull(user.getOldPassword(), "原密码不能为空");
        ParamUtil.checkNull(user.getPassword(), "密码不能为空");
        ParamUtil.checkNull(user.getConfirmPassword(), "确认密码不能为空");
        return R.data(userService.updatePwd(user));
    }

    @DeleteMapping
    @ApiImplicitParam(name = "ids",value = "用户ids",paramType = "query",dataType = "Long",example = "1132123")
    @ApiOperation(value = "用户删除",notes = "用户ids")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public R<Object> delete(@ApiIgnore @RequestBody List<Long> ids){
        ParamUtil.checkNull(ids,"id不能为空");
        return R.status(userService.removeByIds(ids));
    }


}
