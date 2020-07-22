package org.coupon.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.coupon.constant.CouponCategory;
import org.coupon.constant.DistributeTarget;
import org.coupon.constant.ProductLine;
import org.coupon.convert.CouponCategoryConverter;
import org.coupon.convert.DistributeTargetConverter;
import org.coupon.convert.ProductLineConverter;
import org.coupon.convert.TemplateRuleConverter;
import org.coupon.serialization.CouponTemplateSerialize;
import org.coupon.vo.TemplateRule;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 优惠券模板实体
 */
@Entity
@Table(name = "coupon_template")
@Data
@JsonSerialize(using = CouponTemplateSerialize.class)
public class CouponTemplate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**是否可用*/
     @Column(name = "available")
     private boolean available;
    /**是否过期*/
    @Column(name = "expired")
    private boolean expired;
    /**优惠券名称*，唯一*/
    @Column(name = "name")
    private String name;
    /**优惠券logo*/
    @Column(name = "logo")
    private String logo;
    /** 优惠券描述 */
    @Column(name="intro")
    private String desc;
    /**优惠券分类*/
    @Column(name="category")
    @Convert(converter = CouponCategoryConverter.class)
    private CouponCategory category;
    /**产品线*/
    @Column(name = "product_line")
    @Convert(converter = ProductLineConverter.class)
    private ProductLine productLine;
    /**优惠券数量*/
    @Column(name = "coupon_count")
    private Integer count;
    /**创建时间*/
    @Column(name = "create_time")
    private Date createTime;
    /**创建人*/
    @Column(name = "user_id")
    private Long userId;
    /**优惠券模板编码*/
    @Column(name = "template_key")
    private String key;
    /**目标用户*/
    @Column(name = "target")
    @Convert(converter = DistributeTargetConverter.class)
    private DistributeTarget target;
    /**优惠券规则*/
    @Column(name = "rule")
    @Convert(converter = TemplateRuleConverter.class)
    private TemplateRule rule;

    public CouponTemplate(String name,String logo,String desc,String category,
                          Integer productLine,Integer count,Long userId,
                          Integer target,TemplateRule rule){
        this.available = false;
        this.expired = false;
        this.name = name;
        this.logo = logo;
        this.desc = desc;
        this.category = CouponCategory.of(category);
        this.productLine = ProductLine.of(productLine);
        this.count = count;
        this.userId = userId;
        // 优惠券模板唯一编码 = 4(产品线和类型) + 8(日期: 20190101) + id(扩充为4位)
        this.key = productLine.toString()+category
                +DateFormatUtils.format(new Date(),"yyyyMMdd");
        this.target = DistributeTarget.of(target);
        this.rule = rule;
    }
}
