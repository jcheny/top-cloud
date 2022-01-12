package com.ihave.config.auth.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihave.api.R;
import com.ihave.api.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限不足类重写
 *
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/8/3 上午11:01
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            R result = R.fail(ResultCode.UN_AUTHORIZED);
            response.getWriter().write(new ObjectMapper().writeValueAsString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
