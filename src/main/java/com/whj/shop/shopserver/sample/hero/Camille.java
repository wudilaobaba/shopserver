package com.whj.shop.shopserver.sample.hero;

import com.whj.shop.shopserver.sample.HeroTmpl;
import org.springframework.context.annotation.Configuration;



public class Camille implements HeroTmpl {
    private String name;
    private Integer age;

    public Camille(String name,Integer age){
        this.name = name;
        this.age = age;
    }

    public void q(){
        System.out.println("Camille Q");
    }
    public void w(){
        System.out.println("Camille W");
    }
    public void e(){
        System.out.println("Camille E");
    }
    public void r(){
        System.out.println("Camille R");
    }
}
