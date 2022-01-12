package com.ihave.aspect;

import cn.hutool.core.lang.Snowflake;
import com.alibaba.fastjson.JSON;
import com.ihave.utils.IpUtil;
import com.ihave.utils.ObjectUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;

/**
 * @Author: chen
 * @Date: 2021/4/16 10:03 AM
 * @Version 1.0
 */
@Aspect
@Order(2)
@Component
@Slf4j
public class WebAdminLogAspect {

    private static final String defDescription = "该方法没有描述";
    private static final String defNote = "该方法没有备注";
    private static final Long defUserId = 0L;

    Snowflake snowflake = new Snowflake(1,1);

    @Autowired
    private LogService logService;

    //切面表达式
    @Pointcut("execution(* com.ihave.controller.*.*(..))")
    public void setWebLogAspectPointcut() {

    }

    @Value("${spring.application.name}")
    private String serverName;

    //ProceedingJoinPoint 这个是就代表目标方法
    @Around(value = "setWebLogAspectPointcut()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Log _log = new Log();

        //获取执行方法的实例
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        //过去方法上的注解
        ApiOperation annotation = method.getAnnotation(ApiOperation.class);

        //设置方法的描述
        if (annotation == null) {
            _log.setDescription(defDescription);
        } else {
            String value = annotation.value();
            String notes = annotation.notes();
            _log.setDescription(StringUtils.isBlank(value) ? defDescription : value);
            _log.setRemark(StringUtils.isBlank(notes) ? defNote : notes);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //设置用户
        long userId;
        try {
            //注意：如果未登录 userId 将不是long类型哦
            userId = Long.parseLong(authentication.getPrincipal().toString());
        } catch (Exception e) {
            userId = defUserId;
        }

        _log.setUserId(userId);

        //设置方法的消耗时间
        Object result = null;
        StopWatch stopWatch = new StopWatch(); // 创建计时器
        stopWatch.start(); //  开始计时器
        result = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs()); // 不需要我们自己处理这个异常
        stopWatch.stop(); // 记时结束

        //设置方法的消耗时间
        _log.setTime(stopWatch.getTotalTimeMillis());

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        //获取请求request
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        String user_agent = request.getHeader("User-Agent");
        if (ObjectUtil.isEmpty(user_agent)) {
            _log.setAgent("Unknown");
        } else {
            _log.setAgent(user_agent);
        }
        _log.setUrl(request.getServletPath());
        _log.setGroup(serverName);
        _log.setIp(IpUtil.getIpAddr(request));
        _log.setMethod(request.getMethod());
        _log.setCreateTime(new Date());
        _log.setId(snowflake.nextId());
        _log.setParams(getMethodParameter(method, proceedingJoinPoint.getArgs()).toString());
        //保存日志到数据库
        log.info(JSON.toJSONString(_log, true));
        logService.save(_log);
        return result;

    }

    public static Object getMethodParameter(Method method, Object[] args) {
        LocalVariableTableParameterNameDiscoverer
                localVariableTableParameterNameDiscoverer =
                new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = localVariableTableParameterNameDiscoverer.getParameterNames(method);
        HashMap<String, Object> methodParameters = new HashMap<>();
        if (args != null) {
            for (int i = 0; i < (parameterNames != null ? parameterNames.length : 0); i++) {
                if (parameterNames[i].equalsIgnoreCase("password") ||
                        parameterNames[i].equalsIgnoreCase("file") ||
                        parameterNames[i].equalsIgnoreCase("response")) {
                    methodParameters.put(parameterNames[i], "受限制的参数类型");
                } else {
                    if (!parameterNames[i].equalsIgnoreCase("page")) {
                        methodParameters.put(parameterNames[i], args[i] == null ? "null" : JSON.toJSONString(args[i]));
                    }

                }
            }
        }
        return methodParameters;
    }


}
