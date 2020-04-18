/**
 * @作者 7七月
 * @微信公号 林间有风
 * @开源项目 $ http://talelin.com
 * @免费专栏 $ http://course.talelin.com
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020-03-27 21:54
 */
package com.whj.shop.shopserver.repository;

import com.whj.shop.shopserver.modelReal.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    // status = unpaid 延迟消息队列
    // expiredTime > now
    // uid

    Page<OrderEntity> findByExpiredTimeGreaterThanAndStatusAndUserId(Date now, Integer status, Long uid, Pageable pageable);

    Page<OrderEntity> findByUserId(Long uid, Pageable pageable);

    Page<OrderEntity> findByUserIdAndStatus(Long uid, Integer status, Pageable pageable);

    Optional<OrderEntity> findFirstByUserIdAndId(Long uid, Long oid);

    Optional<OrderEntity> findFirstByOrderNo(String orderNo);

    @Modifying
    @Query("update OrderEntity o set o.status=:status where o.orderNo=:orderNo")
    int updateStatusByOrderNo(String orderNo, Integer status);

}
