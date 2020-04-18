/**
 * @作者 7七月
 * @微信公号 林间有风
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2019-08-05 05:28
 */
package com.whj.shop.shopserver.repository;

import com.whj.shop.shopserver.modelReal.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<CouponEntity, Long>, JpaSpecificationExecutor<CouponEntity> {

    @Query("select c from CouponEntity c\n" +
            "join c.categoryList ca\n" +
            "join ActivityEntity a on a.id = c.activityId\n" +
            "where ca.id = :cid\n" +
            "and a.startTime < :now\n" +
            "and a.endTime > :now\n")
    List<CouponEntity> findByCategory(Long cid, Date now);



    @Query("select c from CouponEntity c\n" +
            "join ActivityEntity a on c.activityId = a.id\n" +
            "where c.wholeStore = :isWholeStore\n" +
            "and a.startTime < :now\n" +
            "and a.endTime > :now\n" )

    List<CouponEntity> findByWholeStore(Boolean isWholeStore, Date now);















    /**
     * 查询未过期的优惠券
     */
    @Query("select c From CouponEntity c\n" +
            "join UserCouponEntity uc\n" +
            "on c.id = uc.couponId\n" +
            "join UserEntity u\n" +
            "on u.id = uc.userId\n" +
            "where u.id = :uid\n" +
            "and uc.status = 1\n" +
            "and uc.orderId is null \n" +
            "and c.startTime < :now\n" +
            "and c.endTime > :now")
    List<CouponEntity> findMyAvailable(@Param("uid") Long uid, @Param("now") Date now);


    @Query("select c From CouponEntity c\n" +
            "join UserCouponEntity uc\n" +
            "on c.id = uc.couponId\n" +
            "join UserEntity u\n" +
            "on u.id = uc.userId\n" +
            "where u.id = :uid\n" +
            "and uc.status = 2\n" +
            "and uc.orderId is not null \n" +
            "and c.startTime < :now\n" +
            "and c.endTime > :now")
    List<CouponEntity> findMyUsed(@Param("uid") Long uid, @Param("now") Date now);

    @Query("select c From CouponEntity c\n" +
            "join UserCouponEntity uc\n" +
            "on c.id = uc.couponId\n" +
            "join UserEntity u\n" +
            "on u.id = uc.userId\n" +
            "where u.id = :uid\n" +
            "and uc.orderId is null\n" +
            "and uc.status <> 2\n" +
            "and c.endTime < :now")
    List<CouponEntity> findUserExpired(Long uid, Date now);

    Optional<CouponEntity> findByIdAndCreateTimeLessThanAndEndTimeGreaterThan(Long id, Date now1, Date now2);

}
