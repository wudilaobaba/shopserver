package com.whj.shop.shopserver.sample;


import com.whj.shop.shopserver.sample.condition.CamilleCondition;
import com.whj.shop.shopserver.sample.condition.DianaCondition;
import com.whj.shop.shopserver.sample.hero.Camille;
import com.whj.shop.shopserver.sample.hero.Diana;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

//普通常规的注解bean
//@Configuration
//public class HeroConfiguration {
//    @Bean
//    public HeroTmpl camille(){ //方法名必须与要返回的实例的类名一致
//        return new Camille("Whj",29);
//    }
//}


//自定义注解：
@Configuration
public class HeroConfiguration {
    //阶段一：
//    @Bean
//    @Conditional(CamilleCondition.class) //参数为实现了Condition接口的类
//    public HeroTmpl camille(){ //方法名必须与要返回的实例的类名一致
//        return new Camille("Whj",29);
//    }
//
//    @Bean
//    @Conditional(DianaCondition.class) //参数为实现了Condition接口的类
//    public HeroTmpl diana(){ //方法名必须与要返回的实例的类名一致
//        return new Diana();
//    }


    //阶段二：
//    @Bean
//    @ConditionalOnProperty(value="hero.condition", havingValue="camille",matchIfMissing = true)
//    //加了matchIfMissing = true，就意味着如果配置文件中没有hero.condition属性，那么就会注入有matchIfMissing = true
//    //的类，就是一个默认值的作用
//    public HeroTmpl camille(){ //方法名必须与要返回的实例的类名一致
//        return new Camille("Whj",29);
//    }
//
//    @Bean
//    @ConditionalOnProperty(value="hero.condition", havingValue="diana")
//    //hero.condition 是配置文件中的
//    public HeroTmpl diana(){ //方法名必须与要返回的实例的类名一致
//        return new Diana();
//    }


    //阶段三：
    @Bean
    @ConditionalOnBean(name="mysql")//如果容器中有mysql，那么camille就会被注入到容器中
    public HeroTmpl camille(){ //方法名必须与要返回的实例的类名一致
        return new Camille("Whj",29);
    }
}
