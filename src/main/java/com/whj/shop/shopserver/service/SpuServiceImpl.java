package com.whj.shop.shopserver.service;

import com.whj.shop.shopserver.model.Spu;
import com.whj.shop.shopserver.modelReal.SpuEntity;
import com.whj.shop.shopserver.repository.SpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpuServiceImpl{
    @Autowired
    private SpuRepository spuRepository;
    public SpuEntity getById(long id){
        return this.spuRepository.findOneById(id);
    }

    //查询列表的分页处理：
    public Page<SpuEntity> getLatestPagingSpu(Integer pageNum, Integer size){
        //分页的处理，三个参数：页码，每页数量，排序方式(根据创建时间倒序排列)
        Pageable page = PageRequest.of(pageNum,size, Sort.by("createTime").descending());
        return this.spuRepository.findAll(page);//JPA自带的方法，不用自己去写
    }

    public Page<SpuEntity> getByCategory(Long cid,Boolean isRoot,Integer pageNum,Integer size){
        Pageable page = PageRequest.of(pageNum,size);
        System.out.println(isRoot);
        if(isRoot){
            System.out.println(666);
            return this.spuRepository.findByRootCategoryIdOrderByCreateTime(cid.intValue(),page);
        }else{
            return this.spuRepository.findByCategoryIdOrderByCreateTimeDesc(cid,page);
        }
    }

    public List<SpuEntity> getAllSpu(){
        return this.spuRepository.findAll();
    }

    public Page<SpuEntity> getAllBySimplePage(Integer pageNum, Integer size){
        Pageable page = PageRequest.of(pageNum,size);
        return this.spuRepository.findAll(page);
    }
}
