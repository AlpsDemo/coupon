package org.coupon.convert;

import org.coupon.constant.CouponCategory;

import javax.persistence.AttributeConverter;
/**
 * <h1>优惠券分类枚举属性转换器</h1>
 * AttributeConverter<X, Y>
 * X: 是实体属性的类型
 * Y: 是数据库字段的类型
 * Created by alps.
 */
public class CouponCategoryConverter implements AttributeConverter<CouponCategory,String> {
    @Override
    public String convertToDatabaseColumn(CouponCategory couponCategory) {
        return couponCategory.getCode();
    }

    @Override
    public CouponCategory convertToEntityAttribute(String code) {
        return CouponCategory.of(code);
    }
}
