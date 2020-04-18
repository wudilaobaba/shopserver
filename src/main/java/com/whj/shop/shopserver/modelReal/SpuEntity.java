package com.whj.shop.shopserver.modelReal;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "spu")
@Where(clause = "delete_time is null and online = 1")
public class SpuEntity extends BaseEntity{
    @Id
    private Long id;
    private String title;
    private String subtitle;
    private Long categoryId;
    private Integer rootCategoryId;
    private Boolean online;
    private String price;
    private Integer sketchSpecId;
    private Integer defaultSkuId;
    private String img;
    private String discountPrice;
    private String description;
    private String tags;
    private Boolean isTest;
//    private Object spuThemeImg;
    private String forThemeImg;

    //配置导航关系：那么与spu相关联的数据都会返回,返回的是实体类中的字段，与真实的数据库不一样，但是更好用！！！！！
    @OneToMany()
    @JoinColumn(name="spuId")
    private List<SkuEntity> skuList;

    @OneToMany()
    @JoinColumn(name="spuId")
    private List<SpuImgEntity> spuImgList;

    @OneToMany()
    @JoinColumn(name="spuId")
    private List<SpuDetailImgEntity> spuDetailImgList;
}
