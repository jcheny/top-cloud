package com.ihave.request;

import com.ihave.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/28 下午3:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "用户注册视图")
public class UserRequest extends User implements Serializable {

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("短信验证码业务类型——修改密码为SMS_UPDATE_PWD")
    private String type;

    @ApiModelProperty("短信验证码")
    private String smsCode;

    @ApiModelProperty("确认密码")
    private String confirmPassword;

    @ApiModelProperty("旧密码")
    private String oldPassword;
}
