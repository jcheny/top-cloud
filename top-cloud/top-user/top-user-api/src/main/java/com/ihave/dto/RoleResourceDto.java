package com.ihave.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/3 下午4:07
 */
@Data
public class RoleResourceDto implements Serializable {

    /**
     * 角色id
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 资源id
     */
    private List<Long> resourceId;

}
