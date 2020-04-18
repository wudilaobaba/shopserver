package com.whj.shop.shopserver.modelReal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.whj.shop.shopserver.util.GenericAndJson;
import com.whj.shop.shopserver.util.ListAndJson;
import com.whj.shop.shopserver.util.MapAndJson;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "sku")
public class SkuEntity extends BaseEntity{
    @Id
    private Long id;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private Boolean online;
    private String img;
    private String title;
    private Long spuId;
    //第二种方式：感觉最好用
    //数组JSON转字符串返回给前端@#¥%=(
//    @Convert(converter = ListAndJson.class)
//    private List<Object> specs;

    //第三种方式：先生成get和set
    private String specs;

    public List<Spec> getSpecs() {
        //将上面的specs字符串反序列化
        if(this.specs == null){
            return Collections.emptyList();
        }
        return GenericAndJson.jsonToObject(this.specs,new TypeReference<List<Spec>>(){});
    }

    public void setSpecs(List<Spec> specs) {
        if(specs.isEmpty()){
            return;
        }
        this.specs = GenericAndJson.objectToJson(specs);
    }

    //    @Convert(converter = MapAndJson.class)//@#¥%=( 单体JSON转字符串返回给前端@#¥%=(
//    private Map<String,Object> test;// 单体JSON转字符串返回给前端@#¥%=(
    private String code;
    private Long stock;
    private Integer categoryId;
    private Integer rootCategoryId;

//第一种方式：
    //自己写一个get方法，让JPA读取该字段的时候返回对象List形式给前端，但是不建议这样写，解决方法看有"@#¥%=("标记的地方
//    public List<Spec> getSpecs(){
//        String specs = this.specs;
//        JackSon
//    }
//
//    public List<Spec> setSpecs(List<Spec> data){
//        String specs = this.specs;
//        JackSon
//    }
    public BigDecimal getActualPrice() {
        return discountPrice == null ? this.price : this.discountPrice;
    }

    @JsonIgnore
    public List<String> getSpecValueList() {
        return this.getSpecs().stream().map(Spec::getValue).collect(Collectors.toList());
    }



}
