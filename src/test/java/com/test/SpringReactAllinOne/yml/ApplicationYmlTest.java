package com.test.SpringReactAllinOne.yml;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationYmlTest {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationTime}")
    private int jwtExpirationTime;

    @Test
    public void yml파일_값가져오기(){
        //given
        //when
        //then
        System.out.println(jwtSecret);
        System.out.println(jwtExpirationTime);
    }
}
