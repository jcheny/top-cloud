package com.ihave.service.impl;

import com.ihave.mapper.AutoMapper;
import com.ihave.model.autocode.BeanColumn;
import com.ihave.model.autocode.TsysTables;
import com.ihave.service.AutoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/9 下午5:43
 */
@Service
public class AutoServiceImpl implements AutoService {

    @Resource
    private AutoMapper autoMapper;

    @Override
    public TsysTables tableNameDesc(String tableName) {
        return autoMapper.queryTable(tableName);
    }

    @Override
    public List<BeanColumn> tableColumnsDesc(String tableName) {
        return autoMapper.queryColumns(tableName);
    }


}
