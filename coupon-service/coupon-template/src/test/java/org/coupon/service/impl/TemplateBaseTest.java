package org.coupon.service.impl;

import com.alibaba.fastjson.JSON;
import org.coupon.TemplateApplicationTests;
import org.coupon.exception.CouponException;
import org.coupon.service.ICouponTemplateBaseService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class TemplateBaseTest extends TemplateApplicationTests {
    @Autowired
    private ICouponTemplateBaseService baseService;

    @Test
    public void testBuildTemplateInfo() throws CouponException {

        System.out.println(JSON.toJSONString(
                baseService.buildTemplateInfo( 10)));
        System.out.println(JSON.toJSONString(
                baseService.buildTemplateInfo( 11)));
    }

    @Test
    public void testFindAllUsableTemplate() {

        System.out.println(JSON.toJSONString(
                baseService.findAllUsableTemplate()
        ));
    }

    @Test
    public void testFindId2TemplateSDK() {

        System.out.println(JSON.toJSONString(
                baseService.findIds2TemplateSDK(Arrays.asList(10, 11))
        ));
    }
}
