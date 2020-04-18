package com.whj.shop.shopserver.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Theme {
    @Id
    private Long id;
    private String title;
    private String name;

    //配置导航属性：
    @ManyToMany
    //规范第三张表的字段命名
    @JoinTable(name="theme_spu",joinColumns = @JoinColumn(name="theme_id"),inverseJoinColumns = @JoinColumn(name="spu_id"))
                  //    表名                                        外键                                             外键
    private List<Spu> spuList;
}
