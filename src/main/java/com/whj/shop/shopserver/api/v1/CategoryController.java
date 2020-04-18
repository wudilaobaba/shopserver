package com.whj.shop.shopserver.api.v1;

import com.whj.shop.shopserver.bo.PageCounter;
import com.whj.shop.shopserver.exception.http.NotFoundException;
import com.whj.shop.shopserver.modelReal.CategoryEntity;
import com.whj.shop.shopserver.modelReal.GridCategoryEntity;
import com.whj.shop.shopserver.service.BannerService;
import com.whj.shop.shopserver.service.CategoryService;
import com.whj.shop.shopserver.service.GridCategoryService;
import com.whj.shop.shopserver.util.CommonUtil;
import com.whj.shop.shopserver.vo.CategoriesAllVO;
import com.whj.shop.shopserver.vo.Paging;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;
    @GetMapping("/all")
    /**
     * 返回原始的字段，单纯的分页返回
     */
    public Paging<CategoryEntity> getAll(
            @RequestParam(defaultValue = "0") Integer start,//如果前端不传参的话就默认传0
            @RequestParam(defaultValue = "2") Integer count //如果前端不传参的话就默认传2
    ){
        PageCounter pageCounter = CommonUtil.converToPageParameter(start,count);
        Page page = categoryService.getAll(pageCounter.getPage(),pageCounter.getCount());
        return new Paging<>(page);
    }

    @GetMapping("/getall")
    public CategoriesAllVO findAll(){
        Map<Integer,List<CategoryEntity>> categories = categoryService.getAll();
        return new CategoriesAllVO(categories);
    }


    @Autowired
    GridCategoryService gridCategoryService;
    @GetMapping("/grid/all")
    public List<GridCategoryEntity> getGridCategoryList(){
        List<GridCategoryEntity> getGridCategoryList = gridCategoryService.gridCategoryEntityList();
        if(getGridCategoryList.isEmpty()){
            throw new NotFoundException(30009);
        }
        return getGridCategoryList;
    }

}
