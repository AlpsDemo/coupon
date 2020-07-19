package org.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.coupon.constant.PeriodType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateRule {
    //过期规则
    private Expiration expiration;
    //折扣规则
    private Discount discount;
    /** 使用范围: 地域 + 商品类型 */
    private Usage usage;
    //一个人最多可以领几张
    private Integer limitation;
    /** 权重(可以和哪些优惠券叠加使用, 同一类的优惠券一定不能叠加): list[], 优惠券的唯一编码 */
    private String weight;

    boolean validate(){
        return expiration.validate() && discount.validate()
                && usage.validate() && limitation > 0
                && StringUtils.isNotEmpty(weight);
    }

    /**
     * 有效期规则
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Expiration{
        //优惠券有效期类型,固定的还是变动的,对应PeriodType的code字段
        private Integer period;
        //有效期间隔,只对变动类型有效
        private Integer gap;
        //截止日期
        private Long deadLine;

        boolean validate(){
            return null != PeriodType.of(period) && gap >0 && deadLine > 0;
        }
    }

    /**
     * 折扣规则,需要配合优惠券类型
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Discount{
        //额度,满减(20),折扣(85),立减(10)
        private Integer quota;
        /**
         * 基准，需要满多少才能用
         */
        private Integer base;

        boolean validate(){
            return quota > 0 && base > 0;
        }
    }

    /**
     * 使用范围规则
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Usage{
        /**
         * 省份
         */
        private String province;
        //城市
        private String city;
        /** 商品类型, list[文娱, 生鲜, 家居, 全品类] */
        private String goodsType;

        boolean validate() {

            return StringUtils.isNotEmpty(province)
                    && StringUtils.isNotEmpty(city)
                    && StringUtils.isNotEmpty(goodsType);
        }
    }
}
