package com.whj.shop.shopserver.api.v1;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.whj.shop.shopserver.exception.http.NotFoundException;
import com.whj.shop.shopserver.modelReal.BannerEntity;
import com.whj.shop.shopserver.modelReal.SpuEntity;
import com.whj.shop.shopserver.modelReal.ThemeEntity;
import com.whj.shop.shopserver.service.BannerService;
import com.whj.shop.shopserver.service.SpuServiceImpl;
import com.whj.shop.shopserver.service.ThemeService;
import com.whj.shop.shopserver.vo.ThemePureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("theme")
@Validated
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @GetMapping("/by/names")

//    @ScopeLevel(8)
    public List<ThemePureVO> getThemeGroupByNames(@RequestParam(name = "names") String names) {
        List<String> nameList = Arrays.asList(names.split(","));
        List<ThemeEntity> themes = themeService.findByNames(nameList);
        List<ThemePureVO> list = new ArrayList<>();
        themes.forEach(theme -> {
            Mapper mapper = DozerBeanMapperBuilder.buildDefault();
            ThemePureVO vo = mapper.map(theme, ThemePureVO.class);
            list.add(vo);
        });
        return list;
    }

    //VIP分组 16
    //User分组 2

    @GetMapping("/name/{name}/with_spu")
    public ThemeEntity getThemeByNameWithSpu(@PathVariable(name = "name") String themeName){
        Optional<ThemeEntity> optionalTheme = this.themeService.findByName(themeName);
        return optionalTheme.orElseThrow(()-> new NotFoundException(30003)); //这样就不用做盘空操作了，如果是空的话就抛异常
    }
}
