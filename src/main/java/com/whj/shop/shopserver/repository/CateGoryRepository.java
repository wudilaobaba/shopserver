package com.whj.shop.shopserver.repository;

import com.whj.shop.shopserver.modelReal.CategoryEntity;
import com.whj.shop.shopserver.modelReal.SpuEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 查询数据库中banner表的类：
 * 操作数据库的层 repository
 */
//@Repository
public interface CateGoryRepository extends JpaRepository<CategoryEntity,Long> {
                                                        //要查询的类，主键
    List<CategoryEntity> findByIsRootOrderByIndexAsc(Boolean isRoot);//参数为是否为根结点
}
