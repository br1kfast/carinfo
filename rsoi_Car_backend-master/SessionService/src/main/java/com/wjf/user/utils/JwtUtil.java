//package com.wjf.user.utils;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.wjf.user.entity.User;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//
//@Data
//@Component
//@ConfigurationProperties(prefix = "jwt")
//public class JwtUtil {
//    /**
//     * TokenKey
//     */
//    public static String tokenHeader;
//    /**
//     * Token前缀字符
//     */
//    public static String tokenPrefix;
//
//    /**
//     * 过期时间为一天
//     * TODO 正式上线更换为15分钟
//     */
//    private static final long EXPIRE_TIME = 24*60*60*1000;
//
//    /**
//     * token私钥
//     */
//    private static final String TOKEN_SECRET = "joijsdfjlsjfljfljl5135313135";
//
//
//
//    //解密token
//    public static boolean verity(String token){
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
//            JWTVerifier verifier = JWT.require(algorithm).build();
//            DecodedJWT jwt = verifier.verify(token);
//            return true;
//        } catch (IllegalArgumentException | JWTVerificationException e) {
//            return false;
//        }
//    }
//
//
//    /**
//     * 获取token中注册信息 Claims
//     */
//    public static Claims getTokenClaim (String token) {
//        try {
//            return Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token).getBody();
//        } catch (Exception e) {
////            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//    /**
//     * 获取用户名从token中
//     */
//    public static User getUsernameFromToken(String token) {
////        return getTokenClaim(token).getSubject();
//        Claims claim = getTokenClaim(token);
//        assert claim != null;
//        return new User(claim.getSubject());
//    }
//
//
//}
