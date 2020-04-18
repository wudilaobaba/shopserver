package com.whj.shop.shopserver.modelReal;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Where(clause = "delete_time is null")
@Table(name = "coupon")
public class CouponEntity extends BaseEntity{
    @Id
    private Long id;
    private Long activityId;
    private String title;
    private Date startTime;
    private Date endTime;
    private String description;
    private BigDecimal fullMoney;
    private BigDecimal minus;
    private BigDecimal rate; //折扣
    private String remark;
    private Boolean wholeStore; //全场券
    private Integer type;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "couponList")
    private List<CategoryEntity> categoryList;
}
