package com.whj.shop.shopserver.repository;

import com.whj.shop.shopserver.modelReal.BannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 查询数据库中banner表的类：
 * 操作数据库的层 repository
 */
//@Repository
public interface BannerRepository extends JpaRepository<BannerEntity,Long> {
                                                        //要查询的类，主键
    BannerEntity findOneById(Long id);
    BannerEntity findOneByName(String name);

}
