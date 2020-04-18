package com.whj.shop.shopserver.model;

import javax.persistence.*;

@Entity
public class BannerItem {
    @Id//设置主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键自增
    private long id;
    private String img;
    private String keyword;
    private Short type;
    private String name;

    //自己写外键
    private long bannerId;

    //如果是单项一对多，那么下方三行不用写
    @ManyToOne  //双向一对多
    @JoinColumn(name="bannerId",insertable = false,updatable = false)
    private Banner banner;

    @Override
    public String toString() {
        return "BannerItem{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", keyword='" + keyword + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
