package com.school.interceptors;

import com.school.utils.JwtUtil;
import com.school.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果是 OPTIONS 请求，直接放行
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        String token=request.getHeader("Authorization");
        try {
            Map<String, Object> claims= JwtUtil.parseToken(token);

            //存储业务数据
            ThreadLocalUtil.set(claims);
            //放行
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清除threadlocal数据
        ThreadLocalUtil.remove();
    }
}
