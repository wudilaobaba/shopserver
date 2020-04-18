package com.whj.shop.shopserver.optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalTest {
    @Test
    public void testOptional(){
        Optional<String> empty = Optional.empty();
//        Optional<String> t1 = Optional.of(null);//参数不能为空，否则报错
        Optional<String> t2 = Optional.ofNullable(null); //参数可以为空，否则报错
//        String s = t2.get(); //如果值为空，那么取值的时候就会报错，很好！！！！！！！！
        t2 = Optional.ofNullable("a");
        t2.ifPresent(t->System.out.println("xxxxxxxxxx"));//t2有值才会执行里面的函数
        t2.ifPresent(System.out::println);//自动打印出t,也就是'a'
//        empty.get();

        t2 = Optional.ofNullable(null);
        String s = t2.orElse("默认值");//orElese取值函数，t2如果是空值才....
        System.out.println(s);


        t2 = Optional.ofNullable("qq");
        String str = t2.map(t->t+"b").orElse("c");
        System.out.println(str);


    }
}
