package com.whj.shop.shopserver.modelReal;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "grid_category")
@Where(clause = "delete_time is null")
public class GridCategoryEntity extends BaseEntity{
    @Id
    private int id;
    private String title;
    private String img;
    private String name;
    private Integer categoryId;
    private Integer rootCategoryId;
}
