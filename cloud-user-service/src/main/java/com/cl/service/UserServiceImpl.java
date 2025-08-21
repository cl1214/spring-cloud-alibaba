package com.cl.service;

import com.cl.utils.JwtUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    public Map<String, String> login(String userName) {
        Map<String, String> subject = new HashMap();
        subject.put("userName", "cl");
        subject.put("userId", "1");
        String token = JwtUtils.createToken(subject);
        subject.put("token", token);
        return subject;
    }
}
