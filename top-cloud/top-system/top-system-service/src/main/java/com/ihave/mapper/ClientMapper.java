package com.ihave.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ihave.entity.Client;
import org.apache.ibatis.annotations.Param;

/** 
* @author SENSETIME\xuxuangan
* @date 2021/7/28 下午5:28
* @version 1.0
*/
public interface ClientMapper extends BaseMapper<Client> {

    int deleteByClientId(@Param(Client.COL_CLIENT_ID) String clientId);

    int updateClient(Client client);
}