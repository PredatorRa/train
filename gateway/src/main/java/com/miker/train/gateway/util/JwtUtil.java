package com.miker.train.gateway.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.GlobalBouncyCastleProvider;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author miker
 * @create 2023-07-08 11:41
 */
public class JwtUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JwtUtil.class);

    // 盐值,每个项目都不同
    private static final String key = "Miker12306";

    /**
     * 创建jwt token
     * @param map
     * @return
     */
    public static String createToken(Map<String,Object> map) {
        LOG.info("开始生成JWT token，map:{}", map.toString());
        GlobalBouncyCastleProvider.setUseBouncyCastle(false);
        DateTime now = DateTime.now();
        DateTime expTime = now.offsetNew(DateField.HOUR, 24);
        Map<String, Object> payload = new HashMap<>();
        // 签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        // 过期时间
        payload.put(JWTPayload.EXPIRES_AT, expTime);
        // 生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        // 内容
        payload.putAll(map);
        String token = JWTUtil.createToken(payload, key.getBytes());
        LOG.info("生成JWT token：{}", token);
        return token;
    }

    /**
     * 校验token合法性
     * @param token
     * @return
     */
    public static boolean validate(String token) {
        try {
            LOG.info("开始JWT token校验，token：{}", token);
            GlobalBouncyCastleProvider.setUseBouncyCastle(false);
            JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
            // validate包含了verify
            boolean validate = jwt.validate(0);
            LOG.info("JWT token校验结果：{}", validate);
            return validate;
        } catch (Exception e) {
            LOG.info("JWT token校验异常",e);
            return false;
        }
    }

    /**
     * 根据token获取原始内容
     * @param token
     * @return
     */
    public static JSONObject getJSONObject(String token) {
        GlobalBouncyCastleProvider.setUseBouncyCastle(false);
        JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
        JSONObject payloads = jwt.getPayloads();
        payloads.remove(JWTPayload.ISSUED_AT);
        payloads.remove(JWTPayload.EXPIRES_AT);
        payloads.remove(JWTPayload.NOT_BEFORE);
        LOG.info("根据token获取原始内容：{}", payloads);
        return payloads;
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("id",1L);
        map.put("mobile","123456");
        createToken(map);

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE2ODg3ODgxOTYsIm1vYmlsZSI6IjEyMzQ1NiIsImlkIjoxLCJ" +
                "leHAiOjE2ODg4NzQ1OTYsImlhdCI6MTY4ODc4ODE5Nn0.LQDwXNoQWOqH2aNCBcr0VMqU3NdMkQl77HWtM5IPdIk";
        validate(token);

        getJSONObject(token);
    }
}
