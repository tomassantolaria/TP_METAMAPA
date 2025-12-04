package com.keycloak.moduloauth.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final IpAccessInterceptor ipAccessInterceptor;

    public WebConfig(IpAccessInterceptor ipAccessInterceptor) {
        this.ipAccessInterceptor = ipAccessInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
        registry.addInterceptor(ipAccessInterceptor)
                .addPathPatterns("/**"); 
    }
}