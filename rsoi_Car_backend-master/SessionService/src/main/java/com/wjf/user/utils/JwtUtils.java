package com.wjf.user.utils;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JunFei
 * @data
 * @purpose
 */

@Slf4j
public class JwtUtils {
    public static final String CLAIM_USER_NAME = "userName";  //需要存放的自定义数据
    public static final String CLAIM_ROLE = "role";
    private static final String SECRET = "session"; //token密钥

    /**
     * 过期时间8小时
     */
    private static final long EXPIRE_TIME = 8 * 60 * 60 * 1000;

    /**
     * 获得token中自定义数据中的用户名
     */
    public static String getUsername(String token) {
        return getClaimValue(token, CLAIM_USER_NAME);
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     */
    public static String getClaimValue(String token, String key) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(key).asString();
        } catch (JWTDecodeException e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    /**
     * 生成令牌token
     */
    public static String sign(String userName,String role) {
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        Date expiresDate = new Date(System.currentTimeMillis() + EXPIRE_TIME); //过期日期
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带user信息
        return JWT.create()
                .withHeader(map) //header
                .withClaim(CLAIM_USER_NAME, userName) //payload
                .withClaim(CLAIM_ROLE,role)
                .withExpiresAt(expiresDate)
                .sign(algorithm); //signature
    }

    /**
     * 从Http请求获取token
     */
    public static String getRequestToken(HttpServletRequest httpRequest) {

       String token = httpRequest.getHeader("Authorization");
        if (StrUtil.isBlank(token)) { //如果为空
            token = httpRequest.getParameter("Authorization");
        }
        //去掉前面的bearer和空格
        return token.substring(7);
    }
}
