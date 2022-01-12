package com.ihave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihave.service.ClientService;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ihave.mapper.ClientMapper;
import com.ihave.entity.Client;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author SENSETIME\xuxuangan
* @date 2021/7/28 下午5:28
* @version 1.0
*/
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper,Client> implements ClientService {

    @Resource
    private ClientMapper clientMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveClient(Client client) {
        try {
            // 判断当前客户端ID是否已经存在数据库中
            List<Client> clients = clientMapper.selectList(new QueryWrapper<Client>().eq("client_id", client.getClientId()));
            if(null != clients && clients.size()>0)
                throw new ApiException("当前客户端已经存在");
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            client.setClientSecret(passwordEncoder.encode(client.getClientSecret()));
            if(!StringUtils.isEmpty(client.getScope()))
                client.setScope("all");
            clientMapper.insert(client);
            return true;
        }catch (Exception e){
            throw new ApiException("保存客户端信息出现异常");
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeClientByIds(List<String> ids) {
        try{
            List<Client> clients;
            for(String clientId:ids){
                // 判断当前客户端ID是否存在，若不存在，则删除失败
                clients = clientMapper.selectList(new QueryWrapper<Client>().eq("client_id", clientId));
                if(null==clients || clients.size()<=0)
                    throw new ApiException("当前客户端ID:"+clientId+"不存在，删除失败");
                clientMapper.deleteByClientId(clientId);
            }
            return true;
        }catch (Exception e){
            throw new ApiException("删除客户端信息出现异常");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Client updateClient(Client client) {
        try{
            // 判断当前客户端ID是否存在
            List<Client> clients = clientMapper.selectList(new QueryWrapper<Client>().eq("client_id", client.getClientId()));
            if(null==clients || clients.size()<=0)
                throw new ApiException("当前客户端ID："+client.getClientId()+"不存在，修改失败");
            clientMapper.updateClient(client);
            return client;
        }catch (Exception e){
            throw new ApiException("更新客户端信息出现异常");
        }
    }
}
