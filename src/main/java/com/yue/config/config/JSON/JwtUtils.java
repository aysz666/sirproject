package com.yue.config.config.JSON;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class JwtUtils {

    //过期时间
    private final Long expire = 604800L;

    //密钥
    private final String secret = "secret";

    //JWT名称
    private final String header = "token";

    //生成Jwt
    public String generateToken(String username){
        Date nowDate = new Date();
//失效时间
        Date expireDate = new Date(nowDate.getTime() + 1000 * expire );

//        构造jwt
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setSubject(username)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)//
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    //解析Jwt
    public Claims getClaimsByToken(String jwt){
        try{
            return Jwts.parser()
                    .setSigningKey(secret) //
                    .parseClaimsJws(jwt)
                    .getBody();
        }catch (Exception e){
            return null;
        }

    }

    //判断Jwt是否过期
    public boolean isTokenExpired(Claims claims){
        return claims.getExpiration().before(new Date());
    }

}