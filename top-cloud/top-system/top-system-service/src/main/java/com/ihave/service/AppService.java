package com.ihave.service;

import com.ihave.entity.App;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  AppService
 * @Title: AppService.java
 * @Package com.sensemobile.service
 * @author CHENYU_自动生成
 * @date 2021-08-16 21:44:51
 **/
public interface AppService extends IService<App>{

    String getAgreement(String appName, String type);
}
