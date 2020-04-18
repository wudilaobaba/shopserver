package com.whj.shop.shopserver.modelReal;

import com.whj.shop.shopserver.model.Banner;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name="banner_item") //建议加上table名字
public class BannerItemEntity extends BaseEntity{
    @Id
    private Long id;
    private String img;
    private String keyword;
    private short type;
    private Long bannerId;
    private String name;
}
