package com.whj.shop.shopserver.dto;


import com.whj.shop.shopserver.dto.validators.PasswordEqual;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;

//@Data //会自动生成set get equals toString hashCode
//@EqualsAndHashCode
//@Getter
//@Setter
//@AllArgsConstructor  //生成含有全部参数的构造函数
//@NoArgsConstructor   //生成无参的构造函数
//@RequiredArgsConstructor //根据下面的@NonNull来生成所有带@NonNull注解的参数的构造函数


@Builder
@Getter
//建议01. 使用@Builder同时搭配@Getter一起使用
//建议02. 使用@Builder就不要使用@NoArgsConstructor了


//@NoArgsConstructor
//@Setter
@PasswordEqual(min=2,max=8,message = "密码不一样！！！") //自定义校验注解
public class PersonDTO {
//    @NonNull//name不能为空
    @Length(min=2,max=10,message = "姓名长度在2～10之间") //前端传值的字段长度范围校验
    private String name;
    private Integer age;

    @Valid  //该注解可以同时执行SchoolDTO中的字段校验
    private SchoolDTO schoolDTO;


    private String password1;
    private String password2;
}
