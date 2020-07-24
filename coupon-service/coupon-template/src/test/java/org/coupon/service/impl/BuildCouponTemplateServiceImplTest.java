package org.coupon.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.time.DateUtils;
import org.coupon.TemplateApplicationTests;
import org.coupon.constant.CouponCategory;
import org.coupon.constant.DistributeTarget;
import org.coupon.constant.PeriodType;
import org.coupon.constant.ProductLine;
import org.coupon.service.IBuildCouponTemplateService;
import org.coupon.vo.CouponTemplateRequest;
import org.coupon.vo.TemplateRule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;


public class BuildCouponTemplateServiceImplTest extends TemplateApplicationTests {

    @Autowired
    private IBuildCouponTemplateService buildTemplateService;

    @Test
    public void testBuildTemplate() throws Exception {

        System.out.println(JSON.toJSONString(
                buildTemplateService.buildTemplate(fakeTemplateRequest())
        ));
        Thread.sleep(5000);
    }


    /**
     * <h2>fake TemplateRequest</h2>
     * */
    private CouponTemplateRequest fakeTemplateRequest() {

        CouponTemplateRequest request = new CouponTemplateRequest();
        request.setName("优惠券模板-" + new Date().getTime());
        request.setLogo("http://www.imooc.com");
        request.setDesc("这是一张优惠券模板");
        request.setCategory(CouponCategory.MANJIAN.getCode());
        request.setProductLine(ProductLine.DAMAO.getCode());
        request.setCount(100);
        request.setUserId(10001L);  // fake user id
        request.setTarget(DistributeTarget.SINGLE.getCode());

        TemplateRule rule = new TemplateRule();
        rule.setExpiration(new TemplateRule.Expiration(
                PeriodType.SHIFT.getCode(),
                1, DateUtils.addDays(new Date(), 60).getTime()
        ));
        rule.setDiscount(new TemplateRule.Discount(5, 1));
        rule.setLimitation(1);
        rule.setUsage(new TemplateRule.Usage(
                "四川省", "成都市",
                JSON.toJSONString(Arrays.asList("文娱", "家居"))
        ));
        rule.setWeight(JSON.toJSONString(Collections.EMPTY_LIST));

        request.setRule(rule);

        return request;
    }
}