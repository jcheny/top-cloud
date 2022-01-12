package com.ihave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihave.entity.Config;
import com.ihave.service.ConfigService;
import com.ihave.vo.ConfigVo;
import com.ihave.wrapper.ConfigWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ihave.mapper.ConfigMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author SENSETIME\xuxuangan
* @date 2021/7/29 上午9:34
* @version 1.0
*/
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {


    @Resource
    private ConfigMapper configMapper;

    @Override
    public ConfigVo getConfigByKey(String type, String code) {
        List<Config> configList = configMapper.selectList(new QueryWrapper<Config>()
                .eq("type",type)
                .eq("code",code));
        if(null == configList || configList.size()<=0){
            return null;
        }
        return ConfigWrapper.build().entityVO(configList.get(0));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveConfig(Config config) {
        try{
            configMapper.insert(config);
            return true;
        }catch (Exception e){
            throw new ApiException("保存新的配置出现异常");
        }
    }
}
