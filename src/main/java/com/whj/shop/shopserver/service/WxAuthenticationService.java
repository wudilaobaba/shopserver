/**
 * @作者 7七月
 * @微信公号 林间有风
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020-03-12 21:09
 */
package com.whj.shop.shopserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whj.shop.shopserver.modelReal.UserEntity;
import com.whj.shop.shopserver.repository.UserRepository;
import com.whj.shop.shopserver.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class WxAuthenticationService {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserRepository userRepository;
//
    @Value("${wx.code2session}")
    private String code2SessionUrl;
    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.appsecret}")
    private String appsecret;
    public String code2Session(String code) {
        //请求微信的路径
        String url = MessageFormat.format(this.code2SessionUrl, this.appid, this.appsecret, code);//将参数带入模板
        RestTemplate rest = new RestTemplate();//在后端请求服务(后端调用服务)
        Map<String, Object> session = new HashMap<>();
        //请求微信服务返回的结果：
        String sessionText = rest.getForObject(url, String.class);//第二个参数与返回值类型相同
        System.out.println("微信接口返回的结果："+sessionText);
        try {
            session = mapper.readValue(sessionText, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return this.registerUser(session);
    }
//
    private String registerUser(Map<String, Object> session) {
        String openid = (String)session.get("openid");
//        if (openid == null){
//            throw new ParameterException(20004);
//        }
        Optional<UserEntity> userOptional = this.userRepository.findByOpenid(openid);
        //如果该用户存在：
        if(userOptional.isPresent()){
            // TODO:返回JWT令牌
            // 数字等级
            return JwtToken.makeToken(userOptional.get().getId());
        }
        //如果该用户不存在：新增一条用户
        UserEntity user = UserEntity.builder()
                .openid(openid)
                .build();
        userRepository.save(user);//数据库的"增"
        // TODO:返回JWT令牌
        Long uid = user.getId();
        return JwtToken.makeToken(uid);
    }
}
