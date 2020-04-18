package com.whj.shop.shopserver.api.v1;

import com.whj.shop.shopserver.dto.TokenDTO;
import com.whj.shop.shopserver.dto.TokenGetDTO;
import com.whj.shop.shopserver.exception.http.NotFoundException;
import com.whj.shop.shopserver.service.WxAuthenticationService;
import com.whj.shop.shopserver.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("token")
public class TokenController {
    @Autowired
    private WxAuthenticationService wxAuthenticationService;
    @PostMapping("")
    public Map<String,String> getToken(
            @RequestBody @Validated TokenGetDTO userData
    ){
        Map<String,String> map = new HashMap<>();
        String token = null;
        switch (userData.getType()){
//            case USER_WX:
            case 0://微信登陆
                //请求微信服务器，得到微信返回的结果如下：
                String res = wxAuthenticationService.code2Session(userData.getAccount());
                token = res;
                System.out.println(res); //{"session_key":"tL4M\/l9NwdT2EOfR\/27wxA==","openid":"oy2Xn5QK_DCRTP3vVA7fHqLGMd4U"}
                //需要获取到openid
                //以上步骤相当于使用微信的token换取微信的openid
                break;
//            case USER_Email:
            case 1://邮箱登陆
                break;
            default:
                throw new NotFoundException(10003);
        }
        map.put("token",token);
        return map;
    }

    //验证Token
    @PostMapping("/verify")
    public Map<String, Boolean> verify(@RequestBody TokenDTO token) {
        Map<String, Boolean> map = new HashMap<>();
        Boolean valid = JwtToken.verifyToken(token.getToken());
        map.put("is_valid", valid);
        return map;
    }
}
