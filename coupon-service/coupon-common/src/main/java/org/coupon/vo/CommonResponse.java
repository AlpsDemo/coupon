package org.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.coupon.constant.ResultCodeEnum;

import java.io.Serializable;

/**
 * <h1>通用响应对象定义</h1>
 * Created by alps.
 */
@Getter
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {

    private final Integer code;
    private final String message;
    private T data;

    public CommonResponse(ResultCodeEnum resultCodeEnum,T data) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.data = data;
    }
    public CommonResponse(T data) {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.message = ResultCodeEnum.SUCCESS.getMessage();
        this.data = data;
    }
}
