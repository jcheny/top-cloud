package com.ihave.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ihave.entity.VersionUpgrade;


/**
 *  VersionUpgradeMapper
 * @author CHENYU_自动生成
 * @email ${email}
 * @date 2021-08-11 10:07:00
 */
public interface VersionUpgradeMapper extends BaseMapper<VersionUpgrade>{


    VersionUpgrade queryMaxVersion();

}