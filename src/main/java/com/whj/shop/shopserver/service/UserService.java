/**
 * @作者 7七月
 * @微信公号 林间有风
 * @开源项目 $ http://talelin.com
 * @免费专栏 $ http://course.talelin.com
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020-03-21 16:01
 */
package com.whj.shop.shopserver.service;

import com.whj.shop.shopserver.core.LocalUser;
import com.whj.shop.shopserver.modelReal.UserEntity;
import com.whj.shop.shopserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserById(Long id) {
        return userRepository.findFirstById(id);
    }






    public void updateUserWxInfo(Map<String, Object> wxUser) {
        UserEntity user =this.getUserById(LocalUser.getUser().getId());
        user.setNickname(wxUser.get("nickName").toString());
        user.setWxProfile(wxUser);
        userRepository.save(user);
    }

    public UserEntity createDevUser(Long uid) {
        UserEntity newUser = UserEntity.builder().unifyUid(uid).build();
        userRepository.save(newUser);
        return newUser;
    }

    public UserEntity getUserByUnifyUid(Long uuid) {
        return userRepository.findByUnifyUid(uuid);
    }
}

