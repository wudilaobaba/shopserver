/**
 * @作者 7七月
 * @微信公号 林间有风
 * @开源项目 $ http://talelin.com
 * @免费专栏 $ http://course.talelin.com
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020-03-26 02:11
 */
package com.whj.shop.shopserver.bo;

import com.whj.shop.shopserver.dto.SkuInfoDTO;
import com.whj.shop.shopserver.modelReal.SkuEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SkuOrderBO {
    private BigDecimal actualPrice;
    private Integer count;
    private Integer categoryId;

    public SkuOrderBO(SkuEntity sku, SkuInfoDTO skuInfoDTO) {
        this.actualPrice = sku.getActualPrice();
        this.count = skuInfoDTO.getCount();
        this.categoryId = sku.getCategoryId();
    }

    public BigDecimal getTotalPrice() {
        return this.actualPrice.multiply(new BigDecimal(this.count));
    }
}
