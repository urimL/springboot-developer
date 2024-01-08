package me.urim.springbootdeveloper.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
//자바 클래스에 property 값 가져와 사용하는 애너테이션
@ConfigurationProperties("jwt")

public class JwtProperties {

    private String issuer;
    private String secretKey;
}