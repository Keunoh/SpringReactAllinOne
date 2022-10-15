package com.test.SpringReactAllinOne.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Date;

@Slf4j
public class JwtTokenProviderTest {
    private final String JWT_SECRET_KEY = "JWTSuperSecretKey";
    private final int JWT_EXPIRATION_TIME = 604800000;


    @Test
    public void 토큰발급_테스트(){
        //given
        String userDetailsId = "testId";
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_TIME);

        //when
        String token = Jwts.builder()
                .setSubject(userDetailsId)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
                .compact();

        String[] seperateToken = token.split("\\.");

        //then
        log.info("JSON WEB TOKEN GENERATED!");
        log.info("JWT HEADER : " + seperateToken[0]);
        log.info("JWT PAYLOAD : " + seperateToken[1]);
        log.info("JWT SIGNATURE : " + seperateToken[2]);
    }
}
