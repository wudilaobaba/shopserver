package com.whj.shop.shopserver.modelReal;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Where(clause = "delete_time is null and online = 1")
@Table(name = "category")
public class CategoryEntity extends BaseEntity{
    @Id
    private Long id;

    private String name;

    private String description;

    private Boolean isRoot;

    private String img;

    private Long parentId;

    private Long index;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "coupon_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "coupon_id"))
    private List<CouponEntity> couponList;
}
