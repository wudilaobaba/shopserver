package com.whj.shop.shopserver.core.configuration;

import com.whj.shop.shopserver.core.hack.AuroPrefixUrlMApping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
@Component
public class AutoFixConfiguration implements WebMvcRegistrations {
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new AuroPrefixUrlMApping();
    }
}
