package com.whj.shop.shopserver.api.v1;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.whj.shop.shopserver.bo.PageCounter;
import com.whj.shop.shopserver.exception.http.NotFoundException;
import com.whj.shop.shopserver.modelReal.SpuEntity;
import com.whj.shop.shopserver.service.SpuServiceImpl;
import com.whj.shop.shopserver.util.CommonUtil;
import com.whj.shop.shopserver.vo.Paging;
import com.whj.shop.shopserver.vo.PagingDozer;
import com.whj.shop.shopserver.vo.SpuSamplifyVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/spu")
@Validated
public class SpuController {
    //真实接口
    @Autowired
    private SpuServiceImpl spuService;
    @GetMapping("/id/{id}/detail")
    public SpuEntity getByName(@PathVariable(name="id") @Positive long id){
        SpuEntity spu = this.spuService.getById(id);
        if(spu == null){
            throw new NotFoundException(30003);
        }
        return spu;
    }
    @GetMapping("/id/{id}/simplify")
    public SpuSamplifyVO getSimplifySpu(@PathVariable(name="id") @Positive long id){
        SpuEntity spu = this.spuService.getById(id);
        SpuSamplifyVO vo = new SpuSamplifyVO();
        BeanUtils.copyProperties(spu,vo); //将spu的属性拷贝给vo;
        return vo;
    }
    @GetMapping("/all")
    public List<SpuEntity> findAllSpu(){
        return this.spuService.getAllSpu();
    }
    @GetMapping("/allBySimplePage")//******************单纯的分页查询，返回全部字段
    public Paging<SpuEntity> findAllBySimplePage(
            @RequestParam(defaultValue = "0") Integer start,//如果前端不传参的话就默认传0
            @RequestParam(defaultValue = "2") Integer count //如果前端不传参的话就默认传2
    ){
        PageCounter pageCounter = CommonUtil.converToPageParameter(start,count);
        Page page = this.spuService.getAllBySimplePage(pageCounter.getPage(),pageCounter.getCount());
        return new Paging<>(page);
    }
    @GetMapping("/latest")
    public PagingDozer<SpuEntity,SpuSamplifyVO> getLatestSpuList(
            @RequestParam(defaultValue = "0") Integer start,//如果前端不传参的话就默认传0
            @RequestParam(defaultValue = "2") Integer count //如果前端不传参的话就默认传2
    ){
         //想要返回VO的数据结构的列表，就要使用第三方库 dozer-core
        PageCounter pageCounter = CommonUtil.converToPageParameter(start,count);
        Page<SpuEntity> page = this.spuService.getLatestPagingSpu(pageCounter.getPage(),pageCounter.getCount());
        return new PagingDozer<>(page,SpuSamplifyVO.class);


        //以下注释很重要：：：：：：！！！！！！！！！！！！！！！！！！
//        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
//        List<SpuSamplifyVO> vos = new ArrayList<>();
//        spuList.forEach(s->{
//            SpuSamplifyVO vo = mapper.map(s,SpuSamplifyVO.class);//深度拷贝列表数据变为vo形式的数据样式
//            vos.add(vo);
//        });
    }

    @GetMapping("/by/category/{id}")
    public PagingDozer<SpuEntity,SpuSamplifyVO> getByCategoryId(
            @PathVariable(name="id") @Positive(message = "{id.positive}") Long id, //错误信息在ValidationMessages.properties文件中
            @RequestParam(name="is_root",defaultValue = "false") Boolean isRoot,
            @RequestParam(name="start",defaultValue = "0") Integer start,
            @RequestParam(name="count",defaultValue = "10") Integer count
    ){
        PageCounter pageCounter = CommonUtil.converToPageParameter(start,count);
        Page<SpuEntity> page = this.spuService.getByCategory(id,isRoot,pageCounter.getPage(),pageCounter.getCount());
        return new PagingDozer<>(page,SpuSamplifyVO.class);
    }

}
