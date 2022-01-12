package com.ihave.wrapper;

import cn.hutool.core.bean.BeanUtil;
import com.ihave.entity.Client;
import com.ihave.support.BaseEntityWrapper;
import com.ihave.vo.ClientVo;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/7/28 下午5:49
 */
public class ClientWrapper extends BaseEntityWrapper<Client, ClientVo> {

    public static ClientWrapper build(){
        return new ClientWrapper();
    }

    @Override
    public ClientVo entityVO(Client entity) {
        ClientVo clientVo = new ClientVo();
        BeanUtil.copyProperties(entity,clientVo);
        return clientVo;
    }
}
