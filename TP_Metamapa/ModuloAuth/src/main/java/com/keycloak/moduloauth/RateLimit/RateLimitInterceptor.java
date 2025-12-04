package com.keycloak.moduloauth.RateLimit;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final Map<String, RateLimiter> limiters = new ConcurrentHashMap<>();

    private final int maxRequests = 5;
    private final int windowSeconds = 60;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String key = resolveKey(request);

        RateLimiter limiter = limiters.computeIfAbsent(
                key, k -> new RateLimiter(maxRequests, windowSeconds)
        );

        if (!limiter.tryAcquire()) {
            response.setStatus(429);
            response.getWriter().write("Rate limit exceeded");
            return false;
        }

        return true;
    }

    private String resolveKey(HttpServletRequest request) {

        return "ip:" + request.getRemoteAddr() + ":" + request.getRequestURI();

    }
}