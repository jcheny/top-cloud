package com.ihave.utils;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogUtil extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
        return IpUtil.getIpAddr();
    }
}
