package org.coupon.service.impl;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.coupon.constant.CommonConstant;
import org.coupon.dao.CouponTemplateDao;
import org.coupon.entity.CouponTemplate;
import org.coupon.service.IAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AsyncServiceImpl implements IAsyncService {

    private final CouponTemplateDao couponTemplateDao;

    private final StringRedisTemplate redisTemplate;

    @Autowired
    public AsyncServiceImpl(CouponTemplateDao couponTemplateDao, StringRedisTemplate redisTemplate) {
        this.couponTemplateDao = couponTemplateDao;
        this.redisTemplate = redisTemplate;
    }


    /**
     * <h2>根据模板异步的创建优惠券码</h2>
     * @param template {@link CouponTemplate} 优惠券模板实体
     */
    @Async("getAsyncExecutor")
    @Override
    @SuppressWarnings("all")
    public void asyncConstructCouponByTemplate(CouponTemplate template) {
        Stopwatch watch = Stopwatch.createStarted();

        Set<String> couponCodes = buildCouponCode(template);

        // imooc_coupon_template_code_1
        String redisKey = String.format("%s%s",
                CommonConstant.RedisPrefix.COUPON_TEMPLATE, template.getId().toString());
        log.info("Push CouponCode To Redis: {}",
                redisTemplate.opsForList().rightPushAll(redisKey, couponCodes));

        template.setAvailable(true);
        couponTemplateDao.save(template);

        watch.stop();
        log.info("Construct CouponCode By Template Cost: {}ms",
                watch.elapsed(TimeUnit.MILLISECONDS));

        // TODO 发送短信或者邮件通知优惠券模板已经可用
        log.info("CouponTemplate({}) Is Available!", template.getId());
    }

    private Set<String> buildCouponCode(CouponTemplate template) {
        return null;
    }
}
