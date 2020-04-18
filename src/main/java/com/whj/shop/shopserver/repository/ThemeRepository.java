package com.whj.shop.shopserver.repository;

import com.whj.shop.shopserver.modelReal.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThemeRepository extends JpaRepository<ThemeEntity, Long> {

    @Query("select t from Theme t where t.name in (:names)")
    List<ThemeEntity> findByNames(List<String> names);
    Optional<ThemeEntity> findByName(String name);
}
