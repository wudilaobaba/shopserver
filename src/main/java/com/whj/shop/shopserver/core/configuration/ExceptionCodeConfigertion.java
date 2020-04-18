package com.whj.shop.shopserver.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@ConfigurationProperties(prefix = "whj") //配置文件中的前缀
@PropertySource(value = "classpath:config/excetion-code.properties")
@Component
public class ExceptionCodeConfigertion {
    private Map<Integer,String> codes = new HashMap<>(); //变量命名必须与配置文件中的值一致

    public void setCodes(Map<Integer, String> codes) {
        this.codes = codes;
    }

    public String getMessage(int code){
        String message = codes.get(code);
        return message;
    }
}
