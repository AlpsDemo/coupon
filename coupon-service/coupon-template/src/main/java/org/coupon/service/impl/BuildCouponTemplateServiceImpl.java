package org.coupon.service.impl;

import org.coupon.dao.CouponTemplateDao;
import org.coupon.entity.CouponTemplate;
import org.coupon.exception.CouponException;
import org.coupon.service.IAsyncService;
import org.coupon.service.IBuildCouponTemplateService;
import org.coupon.vo.CouponTemplateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildCouponTemplateServiceImpl implements IBuildCouponTemplateService {
    private final CouponTemplateDao couponTemplateDao;
    private final IAsyncService asyncService;

    @Autowired
    public BuildCouponTemplateServiceImpl(CouponTemplateDao couponTemplateDao, IAsyncService asyncService) {
        this.couponTemplateDao = couponTemplateDao;
        this.asyncService = asyncService;
    }

    @Override
    public CouponTemplate buildTemplate(CouponTemplateRequest request) throws CouponException {
        // 参数合法性校验
        if (!request.validate()) {
            throw new CouponException("BuildTemplate Param Is Not Valid!");
        }
        // 判断同名的优惠券模板是否存在
        if (null != couponTemplateDao.findByName(request.getName())) {
            throw new CouponException("Exist Same Name Template!");
        }
        CouponTemplate couponTemplate = requestToTemplate(request);
        couponTemplate = couponTemplateDao.save(couponTemplate);
        asyncService.asyncConstructCouponByTemplate(couponTemplate);
        return couponTemplate;
    }

    /**
     * <h2>将 TemplateRequest 转换为 CouponTemplate</h2>
     * */
    private CouponTemplate requestToTemplate(CouponTemplateRequest request) {
        return new CouponTemplate(
                request.getName(),
                request.getLogo(),
                request.getDesc(),
                request.getCategory(),
                request.getProductLine(),
                request.getCount(),
                request.getUserId(),
                request.getTarget(),
                request.getRule()
        );
    }
}
