package com.whj.shop.shopserver.core.hack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * 根据项目目录生成路由: 以api/v1为跟路径，来进行访问，如：http://localhost:8999/v1/banner/test3
 */

public class AuroPrefixUrlMApping extends RequestMappingHandlerMapping {
    @Value("${whj.api-package}")
    private String apiPackagePath;
    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo mappingInfo = super.getMappingForMethod(method, handlerType);
        String packageName = this.gerPreFix(handlerType);
        if(mappingInfo !=null){
            String prefix = this.gerPreFix(handlerType);
            RequestMappingInfo newMappingInfo = RequestMappingInfo.paths(prefix).build().combine(mappingInfo);
            return newMappingInfo;
        }
        return mappingInfo;
    }

    private String gerPreFix(Class<?> handlerType){
        String packageName = handlerType.getPackage().getName();
        String dotPath = packageName.replaceAll(this.apiPackagePath,"");
        return dotPath.replace(".","/");
    }
}
