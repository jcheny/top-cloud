package com.ihave.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ihave.entity.Client;

import java.util.List;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/7/28 下午4:19
 */
public interface ClientService extends IService<Client> {

    boolean saveClient(Client client);

    boolean removeClientByIds(List<String> ids);

    Client updateClient(Client client);
}
