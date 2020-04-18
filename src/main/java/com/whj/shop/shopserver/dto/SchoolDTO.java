package com.whj.shop.shopserver.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class SchoolDTO {
    @Length(min=3,max=10)
    private String schoolName;
}
