package com.whj.shop.shopserver.modelReal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;
@Getter
@Setter
@MappedSuperclass  //表的类想要继承该类，那就必须在此写该注解
public abstract class BaseEntity {
    @JsonIgnore //打上该注解，可以不让带有该注解的字段返回给前端
    @Column(insertable=false, updatable=false)
    private Date createTime;
    @JsonIgnore //打上该注解，可以不让带有该注解的字段返回给前端
    @Column(insertable=false, updatable=false)
    private Date updateTime;
    @JsonIgnore //打上该注解，可以不让带有该注解的字段返回给前端
    private Date deleteTime;


}
