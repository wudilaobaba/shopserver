package com.whj.shop.shopserver.service;

import com.whj.shop.shopserver.modelReal.ThemeEntity;
import com.whj.shop.shopserver.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {
    @Autowired
    private ThemeRepository themeRepository;

    public List<ThemeEntity> findByNames(List<String> names){
        return themeRepository.findByNames(names);
    }

    public Optional<ThemeEntity> findByName(String name) {
        return  themeRepository.findByName(name);
    }

}
