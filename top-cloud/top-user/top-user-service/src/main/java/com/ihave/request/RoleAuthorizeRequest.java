package com.ihave.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色授权 请求的对象
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/9/22 下午4:23
 */
@Data
public class RoleAuthorizeRequest implements Serializable {

    private Long roleId;

    private List<Long> roleHasMenu;

}
