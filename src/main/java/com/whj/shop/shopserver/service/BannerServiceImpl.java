package com.whj.shop.shopserver.service;

import com.whj.shop.shopserver.modelReal.BannerEntity;
import com.whj.shop.shopserver.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerServiceImpl implements BannerService{
    @Autowired
    private BannerRepository bannerRepository;
    public BannerEntity getByName(String name){
        return bannerRepository.findOneByName(name);
    }
}
