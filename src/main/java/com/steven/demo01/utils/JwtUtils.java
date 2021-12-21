package com.steven.demo01.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtUtils {

    /**
     * key
     */
    private static String jwtKey;

    /**
     * 过期时间
     */
    private static Integer expiredTimeIn;

    /**
     * JWT KEY
     *
     * @param jwtKey
     */
    @Value("${coisini.security.jwt-key}")
    public void setJwtKey(String jwtKey) {
        JwtUtils.jwtKey = jwtKey;
    }

    /**
     * 过期时间
     *
     * @param expiredTimeIn
     */
    @Value("${coisini.security.token-expired-in}")
    public void setExpiredTimeIn(Integer expiredTimeIn) {
        JwtUtils.expiredTimeIn = expiredTimeIn;
    }

    /**
     * 生成令牌
     *
     * @param uid 用户id
     * @return
     */
    public static String makeToken(Long uid) {
        return JwtUtils.getToken(uid);
    }

    /**
     * 获取令牌
     *
     * @param uid 用户id
     * @return
     */
    private static String getToken(Long uid) {
        // 指定算法
        Algorithm algorithm = Algorithm.HMAC256(JwtUtils.jwtKey);

        Map<String, Date> dateMap = JwtUtils.calculateExpiredIssues();

        /**
         * withClaim(） 写入自定义数据
         * withExpiresAt() 设置过期时间
         * withIssuedAt() 设置当前时间
         * sign() 签名算法
         */
        return JWT.create()
                .withClaim("uid", uid)
                .withExpiresAt(dateMap.get("expiredTime"))
                .withIssuedAt(dateMap.get("now"))
                .sign(algorithm);
    }

    /**
     * 获取自定义数据
     *
     * @param token
     * @return
     */
    public static Optional<Map<String, Claim>> getClaims(String token) {
        DecodedJWT decodedJWT;

        // 指定算法
        Algorithm algorithm = Algorithm.HMAC256(JwtUtils.jwtKey);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();

        try {
            decodedJWT = jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }

        return Optional.of(decodedJWT.getClaims());
    }

    /**
     * 验证Token
     *
     * @param token
     * @return
     */
    public static boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtUtils.jwtKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }

        return true;
    }

    /**
     * 计算过期时间
     *
     * @return
     */
    private static Map<String, Date> calculateExpiredIssues() {
        Map<String, Date> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.SECOND, JwtUtils.expiredTimeIn);
        // 当前时间
        map.put("now", now);
        // 过期时间
        map.put("expiredTime", calendar.getTime());
        return map;
    }
}
