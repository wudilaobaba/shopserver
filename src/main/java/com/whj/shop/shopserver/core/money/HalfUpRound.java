package com.whj.shop.shopserver.core.money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HalfUpRound implements IMoneyDiscount{
    @Override
    public BigDecimal discount(BigDecimal original, BigDecimal discount) {
        BigDecimal actualMoney = original.multiply(discount);//相当于做乘法
        BigDecimal finalMonet = actualMoney.setScale(2, RoundingMode.HALF_UP);//向上取整
        //  想保留几位小数
        return finalMonet;
    }
}
