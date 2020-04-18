package com.whj.shop.shopserver.sample.hero;

import com.whj.shop.shopserver.sample.HeroTmpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component
//@Primary
public class Diana implements HeroTmpl {
    public void q(){
        System.out.println("Diana Q");
    }
    public void w(){
        System.out.println("Diana W");
    }
    public void e(){
        System.out.println("Diana E");
    }
    public void r(){
        System.out.println("Diana R");
    }
}
