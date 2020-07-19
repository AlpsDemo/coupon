package org.coupon.advice;

import org.coupon.constant.ResultCodeEnum;
import org.coupon.exception.CouponException;
import org.coupon.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = CouponException.class)
    public CommonResponse<String> handlerCouponException(CouponException ex){
        return new CommonResponse<>(ResultCodeEnum.API_EXCEPTION,ex.getMessage());
    }

}
