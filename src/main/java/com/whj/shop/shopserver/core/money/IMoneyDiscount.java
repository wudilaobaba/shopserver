package com.whj.shop.shopserver.core.money;

import java.math.BigDecimal;

public interface IMoneyDiscount {
    BigDecimal discount(BigDecimal original,BigDecimal discount);
        //                           原价                折扣率
}
