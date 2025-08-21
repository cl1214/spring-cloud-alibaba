package com.cl.config;

import com.cl.dto.UserDto;
import com.cl.utils.UserContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Objects;

public class OpenFignRequestInterceptorConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        UserDto user = UserContext.getUser();
        if (Objects.nonNull(user)) {
            template.header("userId", user.getUserId().toString());
            template.header("userName", user.getUserName());
        }
    }
}
