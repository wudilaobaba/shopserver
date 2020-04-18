package com.whj.shop.shopserver.api.v1;
import com.whj.shop.shopserver.core.interceptors.ScopeLevel;
import com.whj.shop.shopserver.dto.PersonDTO;
import com.whj.shop.shopserver.dto.WhjTestDTO;
import com.whj.shop.shopserver.exception.http.ForbiddenException;
import com.whj.shop.shopserver.exception.http.NotFoundException;
import com.whj.shop.shopserver.model.Banner;
import com.whj.shop.shopserver.modelReal.BannerEntity;
import com.whj.shop.shopserver.sample.ConnectTmpl;
import com.whj.shop.shopserver.sample.HeroTmpl;
import com.whj.shop.shopserver.service.BannerService;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/banner") //因为有了AuroPrefixUrlMApping类，以下所有路由的最终访问都是 http://localhost:8999/v1/xxx/test3
@Validated //要进行参数校验才使用该注解，与@Max @Min配合使用
public class BannerController {

    //第一种注入方式：
//    @Autowired(required = false)//false:允许Diana类没有@Component注解
//    private Diana diana;


    //第二种注入方式： 最推荐
//    private Diana diana;
//    @Autowired
//    public BannerController(Diana diana){
//        this.diana = diana;
//    }

    //第三种注入方式：
//    private Diana diana;
//    @Autowired
//    public void setDiana(Diana diana) {
//        this.diana = diana;
//    }

    @Autowired
//    @Qualifier("diana")  //可以使用该注解来指定最终的对象是哪个类
    private HeroTmpl hero;

    //bytype方式和byname方式 注入
    /*
    * bytype：
    * 如果只有一个类实现了HeroTmpl接口，那么不会报错，最终就是这个类的实例
    *
    * byname:
    * 如果只有多个类实现了HeroTmpl接口：
    *   1.如果变量明与某个实现类的名字一致，那么就是该类的实例
    *   2.如果变量明与某个实现类的名字不同，那么会报错
    * */


    //前端返回字符串的方法：http://localhost:8999/test
//    @GetMapping("/test")
    @RequestMapping(value="/test", method={RequestMethod.GET,RequestMethod.POST})
    //限制只能用GET和POST方式访问
    public String test(){
        hero.r();
        return "xxx秘诀5";
    }



    @Autowired
    private ConnectTmpl mysql;
    @RequestMapping(value="/test1", method={RequestMethod.GET,RequestMethod.POST})
    //限制只能用GET和POST方式访问
    public String test1(){
        mysql.connect();
        return "xxx秘诀5";
    }


    //测试统一处理异常 - 未知异常
    @RequestMapping(value="/test2", method={RequestMethod.GET,RequestMethod.POST})
    //限制只能用GET和POST方式访问
    public String test2() {
        throw new RuntimeException("就是报错了");
    }


    //测试统一处理异常 - 已知异常 - HttpException
    @RequestMapping(value="/test3", method={RequestMethod.GET,RequestMethod.POST})
    //限制只能用GET和POST方式访问
    public String test3() {
        throw new ForbiddenException(10001);
    }


    //测试接受传递参数01。
    @RequestMapping(value="/test4/{id}", method={RequestMethod.GET,RequestMethod.POST})
    //限制只能用GET和POST方式访问
    public String test4(
            @PathVariable(name="id") Integer xx,
            @RequestParam String name,
            @RequestBody Map<String,Object> person
    ) {
        System.out.println(person);
        return xx+" - "+name;
    }

    //测试接受传递参数02。 DTO形式
    @RequestMapping(value="/test5/{id}", method={RequestMethod.GET,RequestMethod.POST})
    //限制只能用GET和POST方式访问
    public PersonDTO test5(
//            @PathVariable(name="id") @Max(value=10,message="不可以超过10呀呀呀") Integer xx, //指定最大值
            @PathVariable(name="id") @Range(min=10,max=100,message="不可以超过10～100呀呀呀") Integer xx,//指定值的范围
            @RequestParam @Length(min=8) String name,
            @RequestBody @Validated PersonDTO person  //要用post的形式进行前端传值
    ) {
        PersonDTO p = PersonDTO.builder().name("x").age(2).build();
        return p;
    }





    //真实接口
    @Autowired
    private BannerService bannerServiceImpl;
    @GetMapping("/name/{name}")
    @ScopeLevel(value=1) //只要大于4就可以访问接口，逻辑在拦截器中进行处理
    public BannerEntity getByName(@PathVariable(name="name") String name){
        BannerEntity banner = bannerServiceImpl.getByName(name);
        if(banner == null){
            throw new NotFoundException(30005);
        }
        System.out.println(banner);
        return banner;
    }
}
