package com.lion.jwt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtTests {
    @Value("${custom.jwt.secretKey}")
    private String secretKey;


    @Test
    @DisplayName("secretKey 키를 불러온다.")
    void t1() {
        System.out.println(secretKey);
    }

}
