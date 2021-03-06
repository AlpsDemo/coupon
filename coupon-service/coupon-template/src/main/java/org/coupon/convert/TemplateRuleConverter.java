package org.coupon.convert;

import com.alibaba.fastjson.JSON;
import org.coupon.vo.TemplateRule;

import javax.persistence.AttributeConverter;

public class TemplateRuleConverter implements AttributeConverter<TemplateRule,String> {
    @Override
    public String convertToDatabaseColumn(TemplateRule rule) {
        return JSON.toJSONString(rule);
    }

    @Override
    public TemplateRule convertToEntityAttribute(String rule) {
        return JSON.parseObject(rule,TemplateRule.class);

    }
}
