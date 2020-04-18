/**
 * @作者 7七月
 * @微信公号 林间有风
 * @开源项目 $ http://talelin.com
 * @免费专栏 $ http://course.talelin.com
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020-03-23 20:20
 */
package com.whj.shop.shopserver.repository;

import com.whj.shop.shopserver.modelReal.SkuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkuRepository  extends JpaRepository<SkuEntity, Long> {

    List<SkuEntity> findAllByIdIn(List<Long> ids);
}
