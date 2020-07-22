package org.coupon.convert;

import org.coupon.constant.DistributeTarget;

import javax.persistence.AttributeConverter;

public class DistributeTargetConverter implements AttributeConverter<DistributeTarget,Integer> {
    @Override
    public Integer convertToDatabaseColumn(DistributeTarget target) {
        return target.getCode();
    }

    @Override
    public DistributeTarget convertToEntityAttribute(Integer code) {
        return DistributeTarget.of(code);
    }
}
