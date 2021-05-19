package com.portal.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;

/**
 * @author cheng
 */
public class JwtUtil {

    /**
     * 获取Token
     * @param param 用户信息
     * @param exp 失效时间，单位分钟
     * @return
     */
    public static String encode(String key, Map<String,Object> param, String salt,int exp){
        long endTime = System.currentTimeMillis() + 1000 * exp;
        if(salt!=null){
            key+=salt;
        }
        JwtBuilder jwtBuilder = Jwts.builder().signWith(SignatureAlgorithm.HS256,key);
        jwtBuilder = jwtBuilder.setClaims(param);
        jwtBuilder.setExpiration(new Date(endTime));
        String token = jwtBuilder.compact();
        return token;

    }

    /**
     * 获取Token
     * @param param 用户信息
     * @return
     */
    public static String encode(String key, Map<String,Object> param, String salt){
        long endTime = System.currentTimeMillis() + 1000 * 60 * 1440;
        if(salt!=null){
            key+=salt;
        }
        JwtBuilder jwtBuilder = Jwts.builder().signWith(SignatureAlgorithm.HS256,key);
        jwtBuilder = jwtBuilder.setClaims(param);
        jwtBuilder.setExpiration(new Date(endTime));
        String token = jwtBuilder.compact();
        return token;

    }


//    public  static Map<String,Object>  decode(String token ,String key,String salt){
//        Claims claims=null;
//        if (salt!=null){
//            key+=salt;
//        }
//        try {
//            claims= Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
//        } catch ( JwtException e) {
//            return null;
//        }
//        return  claims;
//    }

    /**
     * 检查Token是否合法
     * @param token
     * @return JWTResult
     */
    public  static boolean decode(String token ,String key,String salt){
        if (salt!=null){
            key+=salt;
        }
        try {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            return true;
        } catch (ExpiredJwtException e) {
            // 在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
            return false;
        } catch (SignatureException e) {
            // 在解析JWT字符串时，如果密钥不正确，将会解析失败，抛出SignatureException异常，说明该JWT字符串是伪造的
            return false;
        } catch (Exception e) {
            return false;
        }

    }



//    public static String getToken(String uid, int exp) {
//        long endTime = System.currentTimeMillis() + 1000 * exp;
//        return Jwts.builder().setSubject(uid).setExpiration(new Date(endTime))
//                .signWith(SignatureAlgorithm.HS256,key).compact();
//    }

    /**
//     * 获取Token
//     * @param uid 用户ID
//     * @return
//     */
//    public static String getToken(String uid) {
//        long endTime = System.currentTimeMillis() + 1000 * 60 * 1440;
//        return Jwts.builder().setSubject(uid).setExpiration(new Date(endTime))
//                .signWith(SignatureAlgorithm.RS512, priKey).compact();
//    }


//    public static JWTResult checkToken(String token) {
//        try {
//            Claims claims = Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token).getBody();
//            String sub = claims.get("sub", String.class);
//            return new JWTResult(true, sub, "合法请求",200);
//        } catch (ExpiredJwtException e) {
//            // 在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
//            return new JWTResult(false, null, "token已过期", 401);
//        } catch (SignatureException e) {
//            // 在解析JWT字符串时，如果密钥不正确，将会解析失败，抛出SignatureException异常，说明该JWT字符串是伪造的
//            return new JWTResult(false, null, "非法请求", 403);
//        } catch (Exception e) {
//            return new JWTResult(false, null, "非法请求",403);
//        }
//    }
//    public static void main(String[] args) {
//        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
//        stringObjectHashMap.put("test","test");
//        String encode = encode("lixue",stringObjectHashMap,"1000");
//        System.out.println(encode);
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ0ZXN0IjoidGVzdCIsImV4cCI6MTYxOTMxODA5NX0.xyFL9t0-P0C01V-ii4qG3-5CNOOXQ-vokIymhdND25M";
//        JWTResult lixue = decode(token, "lixue", "1000");
//        System.out.println(lixue.getCode()+lixue.getMsg());
//    }

}
