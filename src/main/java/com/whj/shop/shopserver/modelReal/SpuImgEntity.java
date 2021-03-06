package com.whj.shop.shopserver.modelReal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "spu_img")
public class SpuImgEntity extends  BaseEntity{
    @Id
    private Long id;
    private String img;
    private Long spuId;

}
