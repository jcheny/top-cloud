package com.ihave.wrapper;

import cn.hutool.core.bean.BeanUtil;
import com.ihave.entity.Config;
import com.ihave.support.BaseEntityWrapper;
import com.ihave.vo.ConfigVo;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/7/29 上午9:55
 */
public class ConfigWrapper extends BaseEntityWrapper<Config, ConfigVo> {

    public static ConfigWrapper build(){
        return new ConfigWrapper();
    }

    @Override
    public ConfigVo entityVO(Config config) {
        ConfigVo configVo = new ConfigVo();
        BeanUtil.copyProperties(config,configVo);
        return configVo;
    }
}
