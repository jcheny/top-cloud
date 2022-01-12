package com.ihave.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihave.api.R;
import com.ihave.aspect.Log;
import com.ihave.aspect.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SENSETIME\xuxuangan
 * @version 1.0
 * @date 2021/7/29 下午2:54
 */
@RestController
@RequestMapping("/log")
@Api(tags = "日志控制器")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 查询日志信息
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询日志信息")
    @PreAuthorize("hasAuthority('LOG_QUERY')")
    public R<IPage<Log>> list(String group,
                              Long userId,
                              String agent,
                              String startTime,
                              String method,
                              String endTime,
                              Page<Log> page){
        return R.data(logService.page(page, new LambdaQueryWrapper<Log>()
                .like(StringUtils.isNotBlank(group),Log::getGroup, group)
                .eq(userId != null,Log::getUserId,userId)
                .like(StringUtils.isNotBlank(agent),Log::getAgent,agent)
                .eq(StringUtils.isNotBlank(method),Log::getMethod,method)
                .le(startTime!=null,Log::getCreateTime,endTime)
                .ge(endTime != null,Log::getCreateTime,startTime)
                .orderByDesc(Log::getCreateTime)
                )
        );
    }

}
