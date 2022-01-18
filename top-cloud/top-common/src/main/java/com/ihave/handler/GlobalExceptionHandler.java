package com.ihave.handler;

import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.ihave.api.R;
import com.ihave.api.ResultCode;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/20 下午4:56
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * api异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = ApiException.class)
    public R handle(ApiException e) {
        e.printStackTrace();
        return R.fail(ResultCode.FAILURE, e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public R handleValidException(Exception e) {
        e.printStackTrace();
        String message = e.getMessage();
        return R.fail(message);
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    public R handleValidException(DuplicateKeyException e) {
        e.printStackTrace();
        String message = e.getMessage();
        assert message != null;
        String[] split = message.split("'");
        return R.fail("值为：" + split[1] + "存在重复");
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public R handleAccessDeniedException(Exception e) {
        String message = e.getMessage();
        return R.fail(ResultCode.UN_AUTHORIZED,message);
    }


}
