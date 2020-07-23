package org.coupon.service.impl;

import org.coupon.dao.CouponTemplateDao;
import org.coupon.entity.CouponTemplate;
import org.coupon.exception.CouponException;
import org.coupon.service.ICouponTemplateBaseService;
import org.coupon.vo.CouponTemplateSDK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CouponTemplateBaseServiceImpl implements ICouponTemplateBaseService {

    private final CouponTemplateDao couponTemplateDao;

    @Autowired
    public CouponTemplateBaseServiceImpl(CouponTemplateDao couponTemplateDao) {
        this.couponTemplateDao = couponTemplateDao;
    }

    @Override
    public CouponTemplate buildTemplateInfo(Integer id) throws CouponException {
        Optional<CouponTemplate> couponTemplate = couponTemplateDao.findById(id);
        if (!couponTemplate.isPresent()) {
            throw new CouponException("Template Is Not Exist: " + id);
        }
        return couponTemplate.get();
    }

    @Override
    public List<CouponTemplateSDK> findAllUsableTemplate() {
        List<CouponTemplate> couponTemplateList = couponTemplateDao.findAllByAvailableAndExpired(true, false);
        return couponTemplateList.stream()
                .map(this::template2TemplateSDK)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, CouponTemplateSDK> findIds2TemplateSDK(Collection<Integer> ids) {
        List<CouponTemplate> couponTemplateList = couponTemplateDao.findAllById(ids);

        return couponTemplateList.stream()
                .map(this::template2TemplateSDK)
                .collect(Collectors.toMap(CouponTemplateSDK::getId, Function.identity()));
    }

    private CouponTemplateSDK template2TemplateSDK(CouponTemplate template) {
        return new CouponTemplateSDK(
                template.getId(),
                template.getName(),
                template.getLogo(),
                template.getDesc(),
                template.getCategory().getCode(),
                template.getProductLine().getCode(),
                template.getKey(),  // 并不是拼装好的 Template Key
                template.getTarget().getCode(),
                template.getRule()
        );
    }
}
