package com.ihave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihave.entity.Menu;
import com.ihave.entity.RoleMenu;
import com.ihave.mapper.MenuMapper;
import com.ihave.mapper.RoleMenuMapper;
import com.ihave.request.MenuRequest;
import com.ihave.service.MenuService;
import com.ihave.vo.MenuVo;
import com.ihave.vo.RoleHasMenuVo;
import com.ihave.wrapper.MenuWrapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统菜单 MenuService
 *
 * @author CHENYU_自动生成
 * @Title: MenuService.java
 * @Package com.sensemobile.service
 * @date 2021-09-07 10:09:21
 **/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {


    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu saveMenu(MenuRequest request) {
        if (request.getParentId() == null) {
            request.setParentId(0L);
        }
        Menu menu = MenuWrapper.build().requestEntity(request);
        save(menu);
        return menu;
    }

    @Override
    public List<Menu> userListTree() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return menuMapper.selectUserTree(Long.parseLong(authentication.getPrincipal().toString()));
    }

    @Override
    public RoleHasMenuVo menuAndUserTree(Long roleId, Integer type) {
        RoleHasMenuVo roleHasMenuVo = new RoleHasMenuVo();
        List<RoleMenu> roleMenus = roleMenuMapper.selectList(new LambdaQueryWrapper<RoleMenu>().eq(roleId != null, RoleMenu::getRoleId, roleId));
        if (!CollectionUtils.isEmpty(roleMenus)) {
            List<Menu> list = this.list(
                    new LambdaQueryWrapper<Menu>().in(
                            Menu::getId, roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList())
                    ).eq(type != null, Menu::getType, type)
            );
            roleHasMenuVo.setRoleHas(list);
        }

        List<Menu> pageList = this
                .list(new LambdaQueryWrapper<Menu>()
                        .orderByDesc(Menu::getCreateTime)
                );
        List<MenuVo> menuVos = MenuWrapper.build().listNodeVO(pageList);
        roleHasMenuVo.setMenuTree(menuVos);
        return roleHasMenuVo;
    }

    @Override
    public Boolean removeMenu(List<Long> ids) {
        this.removeByIds(ids);
        return true;
    }
}
