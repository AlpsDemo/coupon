package org.coupon.service;

import org.coupon.entity.CouponTemplate;
import org.coupon.exception.CouponException;
import org.coupon.vo.CouponTemplateRequest;

/**
 * 构建优惠券模板接口定义
 */
public interface IBuildCouponTemplateService {

    /**
     * <h2>创建优惠券模板</h2>
     * @param request {@link CouponTemplateRequest} 模板信息请求对象
     * @return {@link CouponTemplate} 优惠券模板实体
     * */
    CouponTemplate buildTemplate(CouponTemplateRequest request)
            throws CouponException;

}
