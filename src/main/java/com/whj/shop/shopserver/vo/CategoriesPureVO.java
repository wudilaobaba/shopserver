package com.whj.shop.shopserver.vo;

import com.whj.shop.shopserver.modelReal.CategoryEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
@Getter
@Setter
public class CategoriesPureVO {
    private Long id;
    private String name;
    private Boolean isRoot;
    private String img;
    private Long parentId;
    private Long index;
    public CategoriesPureVO(CategoryEntity categoryEntity){
        BeanUtils.copyProperties(categoryEntity,this);
    }
}
