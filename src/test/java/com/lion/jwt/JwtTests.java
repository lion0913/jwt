package com.lion.jwt;

import com.lion.jwt.app.jwt.JwtProvider;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JwtTests {
    @Autowired
    JwtProvider jwtProvider;

    @Value("${custom.jwt.secretKey}")
    private String secretKeyPlain;


    @Test
    @DisplayName("secretKeyStr 키를 불러온다.")
    void t1() {
        assertThat(secretKeyPlain).isNotNull();
//        System.out.println(secretKey);
    }

    @Test
    @DisplayName("secretKeyPlain(원문)으로 hmac 암호화 알고리즘에 맞게 secretKey객체를 만들 수 있어야 한다.")
    void t2() {
        String keyBase64Encoded = Base64.getEncoder().encodeToString(secretKeyPlain.getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());

        //hmac : secretKeyPlain을 hmac방식에 맞게 암호화한다.
//        System.out.println(secretKey.getAlgorithm());
        assertThat(secretKey).isNotNull();
    }

    @Test
    @DisplayName("jwtProvider 객체로 시크릿키 객체를 생성할 수 있다.")
    void t3() {
        SecretKey secretKey = jwtProvider.getSecretKey();

        assertThat(secretKey).isNotNull();
    }

    @Test
    @DisplayName("secretKey 객체를 캐싱, 한번만 생성되어야 한다.")
    void t4() {
        SecretKey secretKey1 = jwtProvider.getSecretKey();
        SecretKey secretKey2 = jwtProvider.getSecretKey();

        System.out.println(secretKey1);
        System.out.println(secretKey2);
        assertThat(secretKey1 == secretKey2).isTrue();
    }


}
