package com.cl.interceptor;

import com.cl.dto.UserDto;
import com.cl.utils.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("userId");
        String userName = request.getHeader("userName");
        if (userId != null) {
            UserDto user = new UserDto();
            user.setUserId(Long.valueOf(userId));
            user.setUserName(userName);
            UserContext.setUser(user);
        }
        return true;

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.remove();
    }
}
