package org.coupon.convert;

import org.coupon.constant.ProductLine;

import javax.persistence.AttributeConverter;
/**
 * <h1>产品线枚举属性转换器</h1>
 * AttributeConverter<X, Y>
 * X: 是实体属性的类型
 * Y: 是数据库字段的类型
 * Created by alps.
 */
public class ProductLineConverter implements AttributeConverter<ProductLine,Integer> {
    @Override
    public Integer convertToDatabaseColumn(ProductLine productLine) {
        return productLine.getCode();
    }

    @Override
    public ProductLine convertToEntityAttribute(Integer code) {
        return ProductLine.of(code);
    }
}
