package com.ihave.vo;

import com.ihave.entity.Menu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/9/22 上午10:52
 */
@Data
public class RoleHasMenuVo implements Serializable {

    private List<Menu> roleHas;

    private List<MenuVo> menuTree;

}
