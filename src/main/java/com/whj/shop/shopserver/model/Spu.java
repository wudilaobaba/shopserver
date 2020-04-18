package com.whj.shop.shopserver.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Spu {
    @Id
    private Long id;
    private String title;
    private String subtitle;

    //配置导航属性：单项多对多只需要在一方写 @ManyToMany即可
    @ManyToMany(mappedBy = "spuList") //双向多对多要确定关系的维护端，就要写(mappedBy = "spuList")
    private List<Theme> themeList;
}
