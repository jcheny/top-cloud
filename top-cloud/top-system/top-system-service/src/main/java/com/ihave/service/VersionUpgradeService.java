package com.ihave.service;

import com.ihave.entity.VersionUpgrade;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  VersionUpgradeService
 * @Title: VersionUpgradeService.java 
 * @Package com.sensemobile.service 
 * @author CHENYU_自动生成
 * @email ${email}
 * @date 2021-08-11 10:07:00  
 **/
public interface VersionUpgradeService extends IService<VersionUpgrade>{

    /**
     * 检测更新
     * @param  version
     * @param  appId
     * @return
     */
    VersionUpgrade check(String version,Long appId);
}
