package com.ihave.aspect;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihave.mapper.LogMapper;
import org.springframework.stereotype.Service;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/9/6 下午5:21
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {
}
