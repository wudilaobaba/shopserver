package com.whj.shop.shopserver.dto.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = PasswordValidator.class) //基于该注解的关联类
public @interface PasswordEqual { //自定义注解 校验
    //以下三个是自定义参数，使用注解的时候可以进行自定义传值
    int min() default 4;
    int max() default 6;
    String message() default "两次密码不匹配";


    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
