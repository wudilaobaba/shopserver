package com.whj.shop.shopserver.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpuSamplifyVO {
    private Long id;
    private String title;
    private String subtitle;
    private String img;
    private String forThemeImg;
    private String price;
    private String discountPrice;
    private String description;
    private String tags;
    private Integer sketchSpecId;
}
