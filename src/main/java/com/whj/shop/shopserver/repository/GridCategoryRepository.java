package com.whj.shop.shopserver.repository;

import com.whj.shop.shopserver.modelReal.CategoryEntity;
import com.whj.shop.shopserver.modelReal.GridCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 查询数据库中banner表的类：
 * 操作数据库的层 repository
 */
//@Repository
public interface GridCategoryRepository extends JpaRepository<GridCategoryEntity,Long> {
                                                        //要查询的类，主键
}
