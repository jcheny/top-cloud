package com.ihave.service;

import com.ihave.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ihave.request.RoleAuthorizeRequest;

/**
 * @author  SENSETIME\chenyu.vendor
 * @date  2021/7/21 下午3:09
 * @version 1.0
 */
public interface RoleService extends IService<Role>{

    //获取默认的角色
    Role getDefaultRole();

    Boolean authorize(RoleAuthorizeRequest request);

    //角色追加授权，与上面不同，这个方法采用的是增加的形式
    Boolean authorizeRun(RoleAuthorizeRequest request);
}
