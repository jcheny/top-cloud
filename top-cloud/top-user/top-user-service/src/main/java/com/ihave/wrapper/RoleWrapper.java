package com.ihave.wrapper;

import cn.hutool.core.bean.BeanUtil;
import com.ihave.entity.Role;
import com.ihave.request.RoleRequest;
import com.ihave.support.BaseEntityWrapper;
import com.ihave.vo.RoleVo;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/28 下午2:57
 */
public class RoleWrapper extends BaseEntityWrapper<Role, RoleVo> {

    public static RoleWrapper build(){
        return new RoleWrapper();
    }

    @Override
    public RoleVo entityVO(Role entity) {
        RoleVo roleVo = new RoleVo();
        BeanUtil.copyProperties(entity,roleVo);
        return roleVo;
    }

    public Role requestEntity(RoleRequest request) {
        Role role = new Role();
        BeanUtil.copyProperties(request,role);
        return role;
    }



}
