package com.whj.shop.shopserver.dto;

import com.whj.shop.shopserver.core.enumeration.LoginType;
import com.whj.shop.shopserver.dto.validators.TokenPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TokenGetDTO {
    private Integer type;
    @NotBlank(message = "account不能为空")
    private String account; //就是微信返回的token
    @TokenPassword(max=30,message="{token.password}")
    private String password;


    @Override
    public String toString() {
        return "TokenGetDTO{" +
                "type=" + type +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
