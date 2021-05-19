package com.portal.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.portal.pojo.UserForBase;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtUtils {
    private static final long EXPTRE_TIME= 15 * 60 * 1000;
    /**
     * token私钥
     */
    private static final String TOKEN_SECRET="8ae0d24822ef59d9e75745449b3501bc";
    /**
     * 生成签名
     */
    public static String Sign(String userName,String userID){
        try {
            //过期时间
            Date date=new Date(System.currentTimeMillis()+EXPTRE_TIME);
            //私钥加密算法
            Algorithm algorithm= Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String,Object> header=new HashMap<>(2);
            header.put("typ","JWT");
            header.put("alg","HS256");
            //附带加密的信息
            return JWT.create()
                    .withHeader(header)
                    .withClaim("username",userName)
                    .withClaim("userID",userID)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * token解码,验证权限
     */
    public static boolean verify(String token){
        try{
            Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier=JWT.require(algorithm).build();
            DecodedJWT jwt=verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * token提取内容
     */
    public static UserForBase getBaseUser(String token){
        try{
            Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier=JWT.require(algorithm).build();
            DecodedJWT jwt=verifier.verify(token);
            UserForBase userForBase=new UserForBase();
            userForBase.setUserName(jwt.getClaim("username").toString());
            userForBase.setUserId(Integer.parseInt(jwt.getClaim("userID").toString()));
            return userForBase;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
