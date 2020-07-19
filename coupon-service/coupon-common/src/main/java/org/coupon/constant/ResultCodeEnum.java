package org.coupon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  ResultCodeEnum {
    SUCCESS(200,"success"),
    API_EXCEPTION(300,"api exception"),
    VALIDATION_ERROR(400,"validation error"),
    SYSTEM_ERROR(500,"system error");

    private final Integer code;
    private final String message;
}
