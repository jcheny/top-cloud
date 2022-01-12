package com.ihave.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/2 上午10:25
 */
@Data
public class OauthClientDto implements Serializable {

    /**
     *用于唯一标识每一个客户端
     */
    private String clientId;

    /**
     *用于指定客户端(client)的访问密匙
     */
    private String clientSecret;

}
