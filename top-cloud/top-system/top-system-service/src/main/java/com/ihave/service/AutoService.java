package com.ihave.service;

import com.ihave.model.autocode.BeanColumn;
import com.ihave.model.autocode.TsysTables;

import java.util.List;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/9 下午5:43
 */
public interface AutoService {

    TsysTables tableNameDesc(String tableName);

    List<BeanColumn> tableColumnsDesc(String tableName);
}
