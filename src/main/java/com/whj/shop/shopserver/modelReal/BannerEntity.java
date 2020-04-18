package com.whj.shop.shopserver.modelReal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name="banner") //建议加上table名字
@Where(clause = "delete_time is null")  //查询的时候可以自定义添加sql语句进行条件查询, delete_time不是空的话，就说明该条数据未被删除
public class BannerEntity extends BaseEntity{
    @Id
    private int id;
    private String name;
    private String description;
    private String title;
    private String img;
    @OneToMany
    @JoinColumn(name="bannerId")
    private List<BannerItemEntity> items;
}
