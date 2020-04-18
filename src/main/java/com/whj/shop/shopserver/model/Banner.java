package com.whj.shop.shopserver.model;
//一个类就是数据库下面的一张表

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="banner")
public class Banner {
    @Id//设置主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键自增
    private long id;
    @Column(length = 16)
    private String name;
//    @Transient //该注解不会将discription放在数据表中
    private String discription;
    private String img;
    private String title;

    //mappedBy = "banner" 属性值是多方中的"单方的变量名"
    //如果是单项一对多，@OneToMany不用写
    @OneToMany(mappedBy = "banner") //(fetch = FetchType.EAGER)急加载   //让banner和bannerItem建立 一对多 的表的关系

    //如果是单项一对多，@JoinColumn就要写在单方
    //如果是双向一对多，@JoinColumn就要写在多方
//    @JoinColumn(name="bannerId")//banner 与 bannerItem 之间生成了外键，就不会生成第三张表了
    private List<BannerItem> items;

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", discription='" + discription + '\'' +
                ", img='" + img + '\'' +
                ", title='" + title + '\'' +
                ", items=" + items +
                '}';
    }
}
