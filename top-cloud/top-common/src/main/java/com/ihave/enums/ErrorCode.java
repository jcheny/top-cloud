package com.ihave.enums;

import com.baomidou.mybatisplus.extension.api.IErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/28 下午3:15
 */
@Getter
@AllArgsConstructor
public enum ErrorCode implements IErrorCode {
    ;

    private long code;

    private String msg;

}
