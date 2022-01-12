package com.ihave.request;

import com.ihave.entity.Menu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MenuRequest extends Menu implements Serializable  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long roleId;


}