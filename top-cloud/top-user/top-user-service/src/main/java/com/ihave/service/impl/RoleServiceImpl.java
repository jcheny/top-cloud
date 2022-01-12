package com.ihave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.ihave.constants.SysConstant;
import com.ihave.entity.RoleMenu;
import com.ihave.fegin.ConfigFegin;
import com.ihave.request.RoleAuthorizeRequest;
import com.ihave.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihave.mapper.RoleMapper;
import com.ihave.entity.Role;
import com.ihave.service.RoleService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author  SENSETIME\chenyu.vendor
 * @date  2021/7/21 下午3:09
 * @version 1.0
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

    @Resource
    private ConfigFegin configFegin;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public Role getDefaultRole() {
        String default_role_name = configFegin.getConfig("ROLE", "DEFAULT_ROLE_NAME");
        List<Role> roles = this.list(new LambdaQueryWrapper<Role>().eq(Role::getCode, default_role_name));
        if(CollectionUtils.isEmpty(roles)){
            throw new ApiException(SysConstant.SYSTEM_ERROR);
        }
        return roles.get(0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean authorize(RoleAuthorizeRequest request) {
        List<RoleMenu> roleMenus = buildRoleMenu(request);
        roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId,request.getRoleId()));
        roleMenuService.saveBatch(roleMenus);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean authorizeRun(RoleAuthorizeRequest request) {
        List<RoleMenu> roleMenus = buildRoleMenu(request);
        roleMenuService.saveBatch(roleMenus);
        return true;
    }

    private List<RoleMenu> buildRoleMenu(RoleAuthorizeRequest request){
        List<Long> menuIds = request.getRoleHasMenu();
        return menuIds.stream().map(aLong -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(request.getRoleId());
            roleMenu.setMenuId(aLong);
            return roleMenu;
        }).collect(Collectors.toList());
    }
}
