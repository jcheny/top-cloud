package com.ihave.service;

import com.ihave.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ihave.request.MenuRequest;
import com.ihave.vo.RoleHasMenuVo;

import java.util.List;

/**
 * 系统菜单 MenuService
 * @Title: MenuService.java
 * @Package com.sensemobile.service
 * @author CHENYU_自动生成
 * @date 2021-09-07 10:09:21
 **/
public interface MenuService extends IService<Menu>{

    Menu saveMenu(MenuRequest request);

    //当前用户的菜单树(登录)查询
    List<Menu> userListTree();

    //查询角色当前的菜单关系（角色有那些菜单，菜单树）
    RoleHasMenuVo menuAndUserTree(Long roleId,Integer type);

    Boolean removeMenu(List<Long> ids);
}
