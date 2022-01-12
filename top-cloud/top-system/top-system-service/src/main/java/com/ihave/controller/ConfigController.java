package com.ihave.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihave.api.R;
import com.ihave.constants.SysConstant;
import com.ihave.entity.Config;
import com.ihave.fegin.ConfigFegin;
import com.ihave.service.ConfigService;
import com.ihave.vo.ConfigVo;
import com.ihave.wrapper.ConfigWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/7/29 上午9:36
 */
@RestController
@RequestMapping("/config")
@Api(tags = "系统配置控制器")
public class ConfigController implements ConfigFegin {

    @Autowired
    private ConfigService configService;

    /**
     * 查询配置信息
     * @param parameters
     * @return
     */
    @GetMapping
    @ApiOperation(value="查询配置信息")
    public R<IPage<ConfigVo>> list(@RequestParam Map<String,Object> parameters){
        //分页查询
        Object pageNum = Optional.ofNullable(parameters.get("current")).orElse(SysConstant.DEFAULT_PAGE_NUM);
        Object pageSize = Optional.ofNullable(parameters.get("size")).orElse(SysConstant.DEFAULT_PAGE_SIZE);
        Page<Config> page = new Page<>(Integer.parseInt(pageNum.toString()),Integer.parseInt(pageSize.toString()));

        String type = Optional.ofNullable(parameters.get("type")).orElse("").toString();
        String code = Optional.ofNullable(parameters.get("code")).orElse("").toString();
        String name = Optional.ofNullable(parameters.get("name")).orElse("").toString();
        Page<Config> configPage = configService.page(page, new LambdaQueryWrapper<Config>()
            .like(Config::getType,type)
            .like(Config::getCode,code)
            .like(Config::getName,name));
        return R.data(ConfigWrapper.build().pageVO(configPage));
    }

    /**
     * 由key（type+code）查询配置信息
     * @param type
     * @param code
     * @return
     */
    @GetMapping("/getConfigByKey")
    @ApiOperation(value="由key获取配置信息")
    public R<Object> getConfigByKey(@RequestParam(value = "type")String type,@RequestParam(value="code") String code){
        return R.data(configService.getConfigByKey(type,code));
    }

    /**
     * 添加新的配置信息
     * @param config
     * @return
     */
    @PostMapping
    @ApiOperation(value = "添加新的配置信息")
    public R<Object> save(@RequestBody Config config){
        return R.status(configService.saveConfig(config));
    }

    /**
     * 删除配置信息
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation(value = "删除配置信息")
    public R<Object> delete(@RequestBody List<Long> ids){
        return R.status(configService.removeByIds(ids));
    }

    /**
     * 修改配置信息
     * @param config
     * @return
     */
    @PutMapping
    @ApiOperation(value = "修改配置信息")
    public R<Object> update(@RequestBody Config config){
        return R.status(configService.updateById(config));
    }

    @ApiIgnore
    @Override
    public String getConfig(String type, String code) {
        ConfigVo configByKey = configService.getConfigByKey(type, code);
        if(configByKey == null){
            return null;
        }
        return configByKey.getValue();
    }
}
