package com.ihave.api;

import com.ihave.constants.SysConstant;
import com.ihave.utils.ObjectUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Optional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletResponse;


/**
 * @author SENSETIME\chenyu.vendor
 * @version 1.0
 * @date 2021/7/20 下午4:10
 */

@Getter
@Setter
@ToString
@ApiModel(description = "返回信息")
@NoArgsConstructor
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态码", required = true)
    private int code;
    @ApiModelProperty(value = "是否成功", required = true)
    private boolean success;
    @ApiModelProperty(value = "承载数据")
    private T data;
    @ApiModelProperty(value = "返回消息", required = true)
    private String message;

    // 分页返回JSON数据的键
    public static final String PAGE_TOTAL = "total";
    public static final String PAGE_PAGES = "pages";
    public static final String PAGE_DATAS = "data";

    private R(IResultCode resultCode) {
        this(resultCode, null, resultCode.getMessage());
    }

    private R(IResultCode resultCode, String message) {
        this(resultCode, null, message);
    }

    private R(IResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMessage());
    }

    private R(IResultCode resultCode, T data, String message) {
        this(resultCode.getCode(), data, message);
    }

    private R(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.success = ResultCode.SUCCESS.code == code;
    }

    /**
     * 判断返回是否为成功
     *
     * @param result Result
     * @return 是否成功
     */
    public static boolean isSuccess(@Nullable R<?> result) {
        return Optional.ofNullable(result)
                .map(x -> ObjectUtil.nullSafeEquals(ResultCode.SUCCESS.code, x.code))
                .orElse(Boolean.FALSE);
    }

    /**
     * 判断返回是否为成功
     *
     * @param result Result
     * @return 是否成功
     */
    public static boolean isNotSuccess(@Nullable R<?> result) {
        return !R.isSuccess(result);
    }

    /**
     * 返回R
     *
     * @param data 数据
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> data(T data) {
        return data(data, SysConstant.DEFAULT_SUCCESS_MESSAGE);
    }

    /**
     * 返回R
     *
     * @param data 数据
     * @param message  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> data(T data, String message) {
        return data(HttpServletResponse.SC_OK, data, message);
    }

    /**
     * 返回R
     *
     * @param code 状态码
     * @param data 数据
     * @param message  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> data(int code, T data, String message) {
        return new R<>(code, data, data == null ? SysConstant.DEFAULT_NULL_MESSAGE : message);
    }

    /**
     * 返回R
     *
     * @param message 消息
     * @param <T> T 泛型标记
     * @return R
     */
    public static <T> R<T> success(String message) {
        return new R<T>(ResultCode.SUCCESS, message);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> success(IResultCode resultCode) {
        return new R<T>(resultCode);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param message        消息
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> success(IResultCode resultCode, String message) {
        return new R<T>(resultCode, message);
    }

    /**
     * 返回R.success（带有data）
     * @param resultCode
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> R<T> success(IResultCode resultCode, T data, String message){
        return new R<T>(resultCode,data,message);
    }

    /**
     * 返回R
     *
     * @param message 消息
     * @param <T> T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(String message) {
        return new R<T>(ResultCode.FAILURE, message);
    }


    /**
     * 返回R
     *
     * @param code 状态码
     * @param message  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(int code, String message) {
        return new R<>(code, null, message);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(IResultCode resultCode) {
        return new R<T>(resultCode);
    }

    /**
     * 返回R
     *
     * @param resultCode 业务代码
     * @param message        消息
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(IResultCode resultCode, String message) {
        return new R<T>(resultCode, message);
    }

    /**
     * 返回R
     *
     * @param flag 成功状态
     * @return R
     */
    public static <T> R<T> status(boolean flag) {
        return flag ? success(SysConstant.DEFAULT_SUCCESS_MESSAGE) : fail(SysConstant.DEFAULT_FAILURE_MESSAGE);
    }


}
