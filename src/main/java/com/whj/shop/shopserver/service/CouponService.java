/**
 * @作者 7七月
 * @微信公号 林间有风
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2019-08-05 05:37
 */
package com.whj.shop.shopserver.service;

import com.whj.shop.shopserver.core.enumeration.CouponStatus;
import com.whj.shop.shopserver.exception.http.NotFoundException;
import com.whj.shop.shopserver.exception.http.ParameterException;
import com.whj.shop.shopserver.modelReal.ActivityEntity;
import com.whj.shop.shopserver.modelReal.CouponEntity;
import com.whj.shop.shopserver.modelReal.UserCouponEntity;
import com.whj.shop.shopserver.repository.ActivityRepository;
import com.whj.shop.shopserver.repository.CouponRepository;
import com.whj.shop.shopserver.repository.UserCouponRepository;
import com.whj.shop.shopserver.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponService {
    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserCouponRepository userCouponRepository;

    public List<CouponEntity> getByCategory(Long cid) {
        Date now = new Date();
        return couponRepository.findByCategory(cid, now);
    }

    public List<CouponEntity> getWholeStoreCoupons() {
        Date now = new Date();
        return couponRepository.findByWholeStore(true, now);
    }

    public void collectOneCoupon(Long uid, Long couponId){
        this.couponRepository
                .findById(couponId)
                .orElseThrow(() -> new NotFoundException(40003));

        ActivityEntity activity = this.activityRepository
                .findByCouponListId(couponId)
                .orElseThrow(() -> new NotFoundException(40010));

        Date now = new Date();
        Boolean isIn = CommonUtil.isInTimeLine(now, activity.getStartTime(), activity.getEndTime());
        if(!isIn){
            throw new ParameterException(40005);
        }

        this.userCouponRepository
                .findFirstByUserIdAndCouponId(uid, couponId)
                .orElseThrow(() -> new ParameterException(40006));

        UserCouponEntity userCouponNew = UserCouponEntity.builder()
                .userId(uid)
                .couponId(couponId)
                .status(CouponStatus.AVAILABLE.getValue())
                .createTime(now)
                .build();
        userCouponRepository.save(userCouponNew);
    }



    public List<CouponEntity> getMyAvailableCoupons(Long uid) {
        Date now = new Date();
        return couponRepository.findMyAvailable(uid, now);
    }

    public List<CouponEntity> getMyUsedCoupons(Long uid) {
        Date now = new Date();
        return couponRepository.findMyUsed(uid, now);
    }

    public List<CouponEntity> getMyExpiredCoupons(Long uid) {
        Date now = new Date();
        return couponRepository.findUserExpired(uid, now);
    }


//    public void writeOff(Long couponId) {
//
//    }

//    @Transactional
//    public void collectOneCoupon(Long uid, Long couponId) {
//        Date now = new Date();
//        // 很好的Optional案例
//        this.couponRepository
//                .findById(couponId)
//                .orElseThrow(()->new NotFound(40003));
//        Optional<Activity> optionalActivity = this.activityRepository.findByCouponListId(couponId);
//        Activity activity = optionalActivity
//                .orElseThrow(()->new NotFound(40010));
//
//        // TODO：有问题，应该判断活动是否过期
//        Boolean isIn = CommonUtil.isInTimeLine(now, activity.getStartTime(), activity.getEndTime());
//        if (!isIn) {
//            throw new ParameterException(40005);
//        }
//        UserCoupon userCoupon = userCouponRepository.findFirstByUserIdAndCouponId(uid, couponId);
//        if (userCoupon != null) {
//            throw new ParameterException(40006);
//        }
//        UserCoupon userCouponNew = UserCoupon.builder()
//                .couponId(couponId)
//                .userId(uid)
//                .status(CouponStatus.AVAILABLE.value())
//                .createTime(now)
//                .build();
//        userCouponRepository.save(userCouponNew);
//    }

//    public Coupon getCoupon(Long id) {
//        return couponRepository.getOne(id);
//    }


//    public List<Coupon> getListByActivityId(Long aid) {
//        return null;
////        return couponRepository.findAllByActivityListId(aid);
//    }

}
