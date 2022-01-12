package com.ihave.service.impl;

import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.ihave.enums.AgreementEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihave.mapper.AppMapper;
import com.ihave.entity.App;
import com.ihave.service.AppService;

import javax.annotation.Resource;

/**
 *  AppService
 * @Title: AppService.java
 * @Package com.sensemobile.service
 * @author CHENYU_自动生成
 * @date 2021-08-16 21:44:51
 **/
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App> implements AppService{

    @Resource
    private AppMapper appMapper;

    @Override
    public String getAgreement(String appName, String type) {
        if(StringUtils.isBlank(appName)){
            throw new ApiException("app名称为空");
        }
        if(StringUtils.isBlank(appName)){
            throw new ApiException("协议类型为空，请选择《隐私协议》[privacy]或《用户协议》[user]");
        }
        AgreementEnum agreement = AgreementEnum.getAgreement(type);
        if(agreement == null){
            throw new ApiException("协议类型错误，请选择《隐私协议》[privacy]或《用户协议》[user]");
        }
        String agreementName;
        switch (agreement){
            case USER:
                agreementName = "user_agreement";
                break;
            case PRIVACY:
                agreementName = "privacy_agreement";
                break;
            default:
                throw new ApiException("协议类型错误，请选择《隐私协议》[privacy]或《用户协议》[user]");
        }

        return appMapper.selectAgreement(appName,agreementName);
    }
}
