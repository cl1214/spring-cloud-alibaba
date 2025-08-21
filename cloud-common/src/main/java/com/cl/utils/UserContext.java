package com.cl.utils;

import com.cl.dto.UserDto;

import java.util.Objects;

public class UserContext {

    private static ThreadLocal<UserDto> threadLocal = new ThreadLocal();

    public static void setUser(UserDto user) {
        threadLocal.set(user);
    }

    public static UserDto getUser() {
        return threadLocal.get();
    }

    public static Long getUserId() {
        if (Objects.isNull(threadLocal.get())) {
            return null;
        }
        return threadLocal.get().getUserId();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
