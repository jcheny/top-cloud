package com.ihave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihave.mapper.VersionUpgradeMapper;
import com.ihave.entity.VersionUpgrade;
import com.ihave.service.VersionUpgradeService;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * VersionUpgradeService
 *
 * @author CHENYU_自动生成
 * @Title: VersionUpgradeService.java 
 * @Package com.sensemobile.service 
 * @email ${email}
 * @date 2021-08-11 10:07:00  
 **/
@Service
public class VersionUpgradeServiceImpl extends ServiceImpl<VersionUpgradeMapper, VersionUpgrade> implements VersionUpgradeService {

    @Resource
    private VersionUpgradeMapper versionUpgradeMapper;

    @Override
    public VersionUpgrade check(String version, Long appId) {
        List<VersionUpgrade> list = this.list(new LambdaQueryWrapper<VersionUpgrade>()
                .eq(VersionUpgrade::getStatus, 0)
                .eq(VersionUpgrade::getVersionCode, version)
                .eq(VersionUpgrade::getAppId, appId));
        VersionUpgrade upgrade = upgrade();
        if (CollectionUtils.isEmpty(list)) {
            return upgrade;
        }

        VersionUpgrade versionUpgrade = list.get(0);
        if (versionUpgrade.getVersionId() < upgrade.getVersionId()) {
            return upgrade;
        } else if (versionUpgrade.getVersionId().equals(upgrade.getVersionId())) {
            if (versionUpgrade.getVersionMini() < upgrade.getVersionMini()) {
                return upgrade;
            }
        }
        return null;
    }

    /**
     * 查询当前的最大版本
     *
     * @return
     */
    private VersionUpgrade upgrade() {
        return versionUpgradeMapper.queryMaxVersion();
    }
}
