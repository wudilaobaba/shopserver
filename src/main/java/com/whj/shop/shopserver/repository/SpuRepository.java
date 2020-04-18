package com.whj.shop.shopserver.repository;

import com.whj.shop.shopserver.modelReal.BannerEntity;
import com.whj.shop.shopserver.modelReal.SpuEntity;
import com.whj.shop.shopserver.vo.SpuSamplifyVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 查询数据库中banner表的类：
 * 操作数据库的层 repository
 */
//@Repository
public interface SpuRepository extends JpaRepository<SpuEntity,Long> {
                                                        //要查询的类，主键
    SpuEntity findOneById(Long id);
    Page<SpuEntity> findByCategoryIdOrderByCreateTimeDesc(Long cid, Pageable pageable);//注意命名规则！！！！很重要
    Page<SpuEntity> findByRootCategoryIdOrderByCreateTime(Integer cid, Pageable pageable);
}
