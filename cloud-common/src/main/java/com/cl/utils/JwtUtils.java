package com.cl.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtils {

    public static String secret = "AIITHEXLABLVSEZHINENGJIANZHU";

    public JwtUtils() {
    }

    /**
     * 生成token
     * @param subject
     * @return
     */
    public static String createToken(Map<String, String> subject){
        // 过期时间
        JWTCreator.Builder builder = JWT.create();
        subject.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        // 设置过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        builder.withExpiresAt(calendar.getTime());
        builder.withIssuer("cl-test");
        return builder.sign(Algorithm.HMAC256(secret));
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public static DecodedJWT verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
    }

    /**
     * 获取token信息
     * @param token
     * @return
     */
    public static Map<String, Claim> getTokenInfo(String token) {
        return JWT.require(Algorithm.HMAC256(secret)).build().verify(token).getClaims();
    }
}
