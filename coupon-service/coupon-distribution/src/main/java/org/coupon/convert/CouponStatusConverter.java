package org.coupon.convert;

import org.coupon.constant.CouponStatus;

import javax.persistence.AttributeConverter;

public class CouponStatusConverter implements AttributeConverter<CouponStatus,Integer> {
    @Override
    public Integer convertToDatabaseColumn(CouponStatus couponStatus) {
        return couponStatus.getCode();
    }

    @Override
    public CouponStatus convertToEntityAttribute(Integer code) {
        return CouponStatus.of(code);
    }
}
