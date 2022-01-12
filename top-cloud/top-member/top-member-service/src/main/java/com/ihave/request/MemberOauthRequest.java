package com.ihave.request;

import com.ihave.entity.MemberOauth;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MemberOauthRequest extends MemberOauth implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("code码")
    private String code;

    @ApiModelProperty("state码")
    private String state;
}