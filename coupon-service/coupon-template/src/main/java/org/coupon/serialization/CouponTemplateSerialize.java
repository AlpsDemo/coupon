package org.coupon.serialization;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.coupon.constant.CommonConstant;
import org.coupon.entity.CouponTemplate;

import java.io.IOException;

public class CouponTemplateSerialize extends JsonSerializer<CouponTemplate> {
    @Override
    public void serialize(CouponTemplate template, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
        generator.writeStartObject();
        generator.writeStringField("id", template.getId().toString());
        generator.writeStringField("name", template.getName());
        generator.writeStringField("logo", template.getLogo());
        generator.writeStringField("desc", template.getDesc());
        generator.writeStringField("category",
                template.getCategory().getDescription());
        generator.writeStringField("productLine",
                template.getProductLine().getDescription());
        generator.writeStringField("count", template.getCount().toString());
        generator.writeStringField("createTime",
                DateFormatUtils.format(template.getCreateTime(), CommonConstant.DATE_TIME_PATTERN));
        generator.writeStringField("userId", template.getUserId().toString());
        generator.writeStringField("key",
                template.getKey() + String.format("%04d", template.getId()));
        generator.writeStringField("target",
                template.getTarget().getDescription());
        generator.writeStringField("rule",
                JSON.toJSONString(template.getRule()));
        generator.writeEndObject();
    }
}
