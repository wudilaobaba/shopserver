/**
 * @作者 7七月
 * @微信公号 林间有风
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2019-08-22 18:56
 */
package com.whj.shop.shopserver.vo;

import com.whj.shop.shopserver.modelReal.CategoryEntity;
import com.whj.shop.shopserver.modelReal.CouponEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CouponCategoryVO extends CouponPureVO {
    private List<CategoryPureVO> categories = new ArrayList<>();

    public CouponCategoryVO(CouponEntity coupon) {
        super(coupon);
        List<CategoryEntity> categories = coupon.getCategoryList();
        categories.forEach(category -> {
            CategoryPureVO vo = new CategoryPureVO(category);
            this.categories.add(vo);
        });
    }
}
