package com.ihave.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ihave.entity.Config;
import com.ihave.vo.ConfigVo;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/7/29 上午9:36
 */
public interface ConfigService extends IService<Config> {

    ConfigVo getConfigByKey(String type,String code);

    boolean saveConfig(Config config);

}
