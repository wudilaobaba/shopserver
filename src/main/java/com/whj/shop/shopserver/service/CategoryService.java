package com.whj.shop.shopserver.service;

import com.whj.shop.shopserver.modelReal.CategoryEntity;
import com.whj.shop.shopserver.modelReal.SpuEntity;
import com.whj.shop.shopserver.repository.CateGoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {
    @Autowired
    private CateGoryRepository cateGoryRepository;
    //查询列表的分页处理：
    public Page<CategoryEntity> getAll(Integer pageNum, Integer size){
        //分页的处理，三个参数：页码，每页数量，排序方式(根据创建时间倒序排列)
        Pageable page = PageRequest.of(pageNum,size, Sort.by("createTime").descending());
        return this.cateGoryRepository.findAll(page);//JPA自带的方法，不用自己去写
    }

    public Map<Integer,List<CategoryEntity>> getAll(){
        List<CategoryEntity> roots = cateGoryRepository.findByIsRootOrderByIndexAsc(true); //查询根结点
        List<CategoryEntity> subs = cateGoryRepository.findByIsRootOrderByIndexAsc(false); //查询子节点
        Map<Integer,List<CategoryEntity>> categories = new HashMap<>();
        categories.put(1,roots);
        categories.put(2,subs);
        return categories;
    }
}
