package com.ihave.service;

import com.ihave.entity.AutoCode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  AutoCodeService
 * @Title: AutoCodeService.java
 * @Package com.ihave.service
 * @author CHENYU_自动生成
 * @date 2022-01-12 10:49:34
 **/
public interface AutoCodeService extends IService<AutoCode>{

    /**
     * 根据id检查数据
     * @param id
     * @return
     */
    Object checkData(Long id);

    /**
     * 生成数据
     * @param id
     * @return
     */
    void autoData(Long id);
}
