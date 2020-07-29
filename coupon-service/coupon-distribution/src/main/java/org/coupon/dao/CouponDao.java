package org.coupon.dao;

import org.coupon.constant.CouponStatus;
import org.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponDao extends JpaRepository<Coupon,Integer> {

    List<Coupon> findByUserIdAndStatus(Long userId, CouponStatus status);
}
