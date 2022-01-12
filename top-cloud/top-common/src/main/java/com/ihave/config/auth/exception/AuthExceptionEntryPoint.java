package com.ihave.config.auth.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihave.api.R;
import com.ihave.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/3 上午10:57
 */
@Component
@Slf4j
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws ServletException {
        log.error(authException.getMessage());
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            R result = R.fail(ResultCode.BAD_TOKEN);
            response.getWriter().write(new ObjectMapper().writeValueAsString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
