package com.whj.shop.shopserver.sample.hero;

import com.whj.shop.shopserver.sample.HeroTmpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component
//@Primary
public class Irelia implements HeroTmpl {
    public void q(){
        System.out.println("Irelia Q");
    }
    public void w(){
        System.out.println("Irelia W");
    }
    public void e(){
        System.out.println("Irelia E");
    }
    public void r(){
        System.out.println("Irelia R");
    }
}
