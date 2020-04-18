package com.whj.shop.shopserver.dto.validators;


import com.whj.shop.shopserver.dto.PersonDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordEqual, PersonDTO> {
    private int min;
    private int max;

    @Override//获取注解中的参数：min,max等
    public void initialize(PasswordEqual constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    //范型的第二个参数是自定义注解修饰的目标的类型
    @Override
    public boolean isValid(PersonDTO personDTO, ConstraintValidatorContext constraintValidatorContext) {
        String password1 = personDTO.getPassword1();
        String password2 = personDTO.getPassword2();
        int passwordLen = password1.length();
        boolean length = passwordLen>=this.min && passwordLen<=this.max;
        boolean match = password1.equals(password2);
        return length && match;
    }
}
