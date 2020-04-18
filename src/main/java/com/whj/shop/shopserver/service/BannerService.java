package com.whj.shop.shopserver.service;


import com.whj.shop.shopserver.modelReal.BannerEntity;

public interface BannerService {
    BannerEntity getByName(String name);
}
