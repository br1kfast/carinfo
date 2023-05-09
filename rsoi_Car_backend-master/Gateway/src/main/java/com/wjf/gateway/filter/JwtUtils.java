package com.wjf.gateway.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author JunFei
 * @data
 * @purpose JWT工具类
 */

@Slf4j
public class JwtUtils {
    public static final String CLAIM_USER_NAME = "userName";
    public static final String CLAIM_ROLE = "role";
    private static final String SECRET = "session";

    /**
     * 过期时间8小时
     */
    private static final long EXPIRE_TIME = 8 * 60 * 60 * 1000;

    /**
     * 获得token中的用户名
     */
    public static String getUsername(String token) {
        return getClaimValue(token, CLAIM_USER_NAME);
    }

    /**
     * 获得token中的角色
     */
    public static String getRole(String token) {
        if("".equals(token) || token == null){
            return "";
        }
        return getClaimValue(token, CLAIM_ROLE);
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
     * 生成签名
     */
    public static String sign(String userName,String role) {
        Date expiresDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带user信息
        return JWT.create().withClaim(CLAIM_USER_NAME, userName).withClaim(CLAIM_ROLE,role).withExpiresAt(expiresDate)
                .sign(algorithm);
    }

    /**
     * 获取请求的token
     */
    public static String getRequestToken(HttpServletRequest httpRequest) {
        return httpRequest.getHeader("Authorization");
    }
}
