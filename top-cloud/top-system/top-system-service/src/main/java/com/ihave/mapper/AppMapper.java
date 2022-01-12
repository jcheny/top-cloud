package com.ihave.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ihave.entity.App;
import org.apache.ibatis.annotations.Param;


/**
 *  AppMapper
 * @author CHENYU_自动生成
 * @email ${email}
 * @date 2021-08-16 21:44:51
 */
public interface AppMapper extends BaseMapper<App>{


    String selectAgreement(@Param("appName") String appName, @Param("agreementName") String agreementName);

}