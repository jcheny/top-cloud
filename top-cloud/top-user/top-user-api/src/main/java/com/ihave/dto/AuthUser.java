package com.ihave.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/26 下午4:23
 */
@Data
public class AuthUser implements Serializable {

    private String uuid;

    private String username;

    private String nickname;

    private String avatar;

    private String blog;

    private String company;

    private String location;

    private String email;

    private String remark;

    private String gender;

    private String source;

    private AuthUserToken token;
}
