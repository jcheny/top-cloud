package com.ihave.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihave.entity.AutoCode;
import com.ihave.mapper.AutoCodeMapper;
import com.ihave.model.autocode.AutoConfigModel;
import com.ihave.model.autocode.BeanColumn;
import com.ihave.model.autocode.TableInfo;
import com.ihave.model.autocode.TsysTables;
import com.ihave.service.AutoCodeService;
import com.ihave.service.AutoService;
import com.ihave.utils.AutoCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  AutoCodeService
 * @Title: AutoCodeService.java
 * @Package com.ihave.service
 * @author CHENYU_自动生成
 * @date 2022-01-12 10:49:34
 **/
@Service
public class AutoCodeServiceImpl extends ServiceImpl<AutoCodeMapper, AutoCode> implements AutoCodeService{

    private static final String author = "AUTO";
    private static final String pid = BigDecimal.ZERO.toString();
    private static final String parentPath = System.getProperty("user.dir");

    @Autowired
    private AutoService autoService;

    @Override
    public Object checkData(Long id) {
        AutoCode autoCode = this.getById(id);
        TsysTables tsysTables = autoService.tableNameDesc(autoCode.getTableName());
        List<BeanColumn> beanColumns = autoService.tableColumnsDesc(autoCode.getTableName());
        Map<String,Object> map = new HashMap<>();
        map.put("tableDesc",tsysTables);
        map.put("columnsDesc",beanColumns);
        return map;
    }

    @Override
    public void autoData(Long id) {
        AutoCode autoCode = this.getById(id);
        TsysTables tsysTables = autoService.tableNameDesc(autoCode.getTableName());
        List<BeanColumn> beanColumns = autoService.tableColumnsDesc(autoCode.getTableName());
        //构建对象
        TableInfo tableInfo = new TableInfo(tsysTables.getTableName(), beanColumns, tsysTables.getTableComment());
        AutoConfigModel autoConfigModel = new AutoConfigModel(tsysTables.getTableName(), tsysTables.getTableComment(),
                author, pid, parentPath + autoCode.getModelName(), beanColumns,autoCode.getGatewayName(),autoCode.getVueName());
        AutoCodeUtil.autoCodeOneModel(tableInfo, autoConfigModel);
    }
}
