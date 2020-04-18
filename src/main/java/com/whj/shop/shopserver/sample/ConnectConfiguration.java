package com.whj.shop.shopserver.sample;

import com.whj.shop.shopserver.sample.DataBase.MySql;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectConfiguration {
    @Value("${mysql.ip}")
    private String ip;
    @Value("${mysql.port}")
    private Integer port;
    @Bean
    public ConnectTmpl mysql(){
        return new MySql(this.ip,this.port);
    }
}
