package org.coupon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.coupon.constant.CouponStatus;
import org.coupon.convert.CouponStatusConverter;
import org.coupon.vo.CouponTemplateSDK;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coupon")
public class Coupon {
    /** 自增主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**关联优惠券模板主键*/
    @Column(name = "template_id",nullable = false)
    private Integer templateId;

    /**领取用户*/
    @Column(name = "user_id",nullable = false)
    private Long userId;

    /**优惠券码*/
    @Column(name = "coupon_code",nullable = false)
    private String couponCode;

    /**
     * 领取时间
     */
    @Column(name = "assign_time")
    private Date assignTime;

    /**优惠券状态*/
    @Column(name = "status")
    @Convert(converter = CouponStatusConverter.class)
    private CouponStatus status;

    /** 用户优惠券对应的模板信息 */
    @Transient
    private CouponTemplateSDK templateSDK;

    /**
     * <h2>返回一个无效的 Coupon 对象</h2>
     * */
    public static Coupon invalidCoupon() {

        Coupon coupon = new Coupon();
        coupon.setId(-1);
        return coupon;
    }

    /**
     * <h2>构造优惠券</h2>
     * */
    public Coupon(Integer templateId, Long userId, String couponCode,
                  CouponStatus status) {

        this.templateId = templateId;
        this.userId = userId;
        this.couponCode = couponCode;
        this.status = status;
    }
}
