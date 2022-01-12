package com.ihave.vo;

import com.ihave.entity.User;
import lombok.Data;

import java.io.Serializable;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/28 下午2:46
 */
@Data
public class UserVo extends User implements Serializable {

    private Long roleId;

    private String roleName;

}
