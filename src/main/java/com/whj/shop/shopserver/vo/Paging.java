package com.whj.shop.shopserver.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 分页对象
 * @param <T>
 */
@Getter
@Setter
public class Paging<T> {
    private Long total;
    private Integer count;
    private Integer page;
    private Integer totalPage;
    private List<T> items;
    public Paging(Page<T> pageT){
        this.initPageParameters(pageT);
        this.items = pageT.getContent();//Page对象返回的查询结果
    }

    public Paging() {
    }

    void initPageParameters(Page<T> pageT){
        this.total = pageT.getTotalElements();
        this.count = pageT.getSize();
        this.page = pageT.getNumber();
        this.totalPage = pageT.getTotalPages();

    }
}
