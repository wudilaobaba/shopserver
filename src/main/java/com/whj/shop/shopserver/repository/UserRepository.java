package com.whj.shop.shopserver.repository;
import com.whj.shop.shopserver.modelReal.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    Optional<UserEntity> findByOpenid(String openid);
    UserEntity findFirstById(Long id);
    UserEntity findByUnifyUid(Long uuid);
}
