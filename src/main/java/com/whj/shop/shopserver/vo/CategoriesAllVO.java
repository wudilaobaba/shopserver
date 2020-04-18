package com.whj.shop.shopserver.vo;

import com.whj.shop.shopserver.modelReal.CategoryEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class CategoriesAllVO {
    private List<CategoriesPureVO> roots;
    private List<CategoriesPureVO> subs;
    public CategoriesAllVO(Map<Integer,List<CategoryEntity>> map){
        //stream流
        this.roots = map.get(1).stream().map(CategoriesPureVO::new).collect(Collectors.toList());
        this.subs = map.get(2).stream().map(CategoriesPureVO::new).collect(Collectors.toList());
    }
}
