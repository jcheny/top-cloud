package com.ihave.mapper;

import com.ihave.model.autocode.BeanColumn;
import com.ihave.model.autocode.TsysTables;

import java.util.List;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/9 下午5:40
 */
public interface AutoMapper {

    /**
     * 查询具体某表信息
     *
     * @param tableName
     * @return
     */
    TsysTables queryTable(String tableName);

    List<BeanColumn> queryColumns(String tableName);
}
