package com.keycloak.moduloauth.Config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class IpAccessInterceptor implements HandlerInterceptor {

    private static final String X_FORWARDED_FOR = "X-Forwarded-For";
    private final IpAccessProperties properties;


    public IpAccessInterceptor(IpAccessProperties properties) {
        this.properties = properties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
       
        String clientIp = request.getHeader(X_FORWARDED_FOR);
        

        if (clientIp == null || clientIp.isEmpty()) {
            clientIp = request.getRemoteAddr(); 
        }


        if (clientIp != null && clientIp.contains(",")) {
            clientIp = clientIp.split(",")[0].trim();
        }
        
    
        if (properties.getBlacklist().contains(clientIp)) {
            System.out.println("BLOQUEADO POR LISTA NEGRA: " + clientIp);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); 
            return false; 
        }
        
        return true; 
    }
}