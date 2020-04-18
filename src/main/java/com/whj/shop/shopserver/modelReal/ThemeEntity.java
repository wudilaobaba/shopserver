package com.whj.shop.shopserver.modelReal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "theme")
public class ThemeEntity extends BaseEntity{
    @Id
    private Long id;
    private String title;
    private String description;
    private String name;
    private String tplName;//模板名称
    private String entranceImg;
    private String extend;
    private String internalTopImg;
    private String titleImg;
    private Boolean online;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="theme_spu", joinColumns = @JoinColumn(name="theme_id")
            , inverseJoinColumns = @JoinColumn(name="spu_id"))
    private List<SpuEntity> spuList;

}
