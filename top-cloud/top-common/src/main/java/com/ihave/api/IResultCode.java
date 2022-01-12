package com.ihave.api;

import java.io.Serializable;

/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/20 下午4:11
 */
public interface IResultCode extends Serializable {
    String getMessage();

    int getCode();
}
