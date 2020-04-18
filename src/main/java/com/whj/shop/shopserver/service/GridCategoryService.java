package com.whj.shop.shopserver.service;

import com.whj.shop.shopserver.modelReal.CategoryEntity;
import com.whj.shop.shopserver.modelReal.GridCategoryEntity;
import com.whj.shop.shopserver.repository.GridCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridCategoryService {
    @Autowired
    private GridCategoryRepository gridCategoryRepository;
    public List<GridCategoryEntity> gridCategoryEntityList(){
        return gridCategoryRepository.findAll();
    }
}
