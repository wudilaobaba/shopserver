/**
 * @作者 7七月
 * @微信公号 林间有风
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2019-08-05 04:25
 */
package com.whj.shop.shopserver.api.v1;

import com.whj.shop.shopserver.core.LocalUser;
import com.whj.shop.shopserver.core.UnifyResponse;
import com.whj.shop.shopserver.core.enumeration.CouponStatus;
import com.whj.shop.shopserver.core.interceptors.ScopeLevel;
import com.whj.shop.shopserver.exception.http.ParameterException;
import com.whj.shop.shopserver.modelReal.CouponEntity;
import com.whj.shop.shopserver.modelReal.UserEntity;
import com.whj.shop.shopserver.service.CouponService;
import com.whj.shop.shopserver.vo.CouponCategoryVO;
import com.whj.shop.shopserver.vo.CouponPureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("coupon")
@RestController
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/by/category/{cid}")
    public List<CouponPureVO> getCouponListByCategory(
            @PathVariable Long cid) {
        List<CouponEntity> coupons = couponService.getByCategory(cid);
        if (coupons.isEmpty()) {
            return Collections.emptyList();
        }
        List<CouponPureVO> vos = CouponPureVO.getList(coupons);
        return vos;
    }

    @GetMapping("/whole_store")
    public List<CouponPureVO> getWholeStoreCouponList() {
        List<CouponEntity> coupons = this.couponService.getWholeStoreCoupons();
        if (coupons.isEmpty()) {
            return Collections.emptyList();
        }
        return CouponPureVO.getList(coupons);
    }

    @ScopeLevel()
    @PostMapping("/collect/{id}")
    public void collectCoupon(@PathVariable Long id) {
        Long uid = LocalUser.getUser().getId();
        couponService.collectOneCoupon(uid, id);
        UnifyResponse.createSuccess(0);
    }



    @ScopeLevel
    @GetMapping("/myself/by/status/{status}")
    public List<CouponPureVO> getMyCouponByStatus(@PathVariable Integer status) {
//        List<Coupon> coupons =
        Long uid = LocalUser.getUser().getId();
        List<CouponEntity> coupons;
        switch (CouponStatus.toType(status)) {
            case AVAILABLE:
                coupons = couponService.getMyAvailableCoupons(uid);
                break;
            case USED:
                coupons = couponService.getMyUsedCoupons(uid);
                break;
            case EXPIRED:
                coupons = couponService.getMyExpiredCoupons(uid);
                break;
            default:
                throw new ParameterException(40001);
        }
        return CouponPureVO.getList(coupons);

    }


    @ScopeLevel()
    @GetMapping("/myself/available/with_category")
    public List<CouponCategoryVO> getUserCouponWithCategory() {
        UserEntity user = LocalUser.getUser();
        List<CouponEntity> coupons = couponService.getMyAvailableCoupons(user.getId());
        if (coupons.isEmpty()) {
            return Collections.emptyList();
        }
        return coupons.stream().map(coupon -> {
            CouponCategoryVO vo = new CouponCategoryVO(coupon);
            return vo;
        }).collect(Collectors.toList());
    }


}
