package me.urim.springbootdeveloper.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import me.urim.springbootdeveloper.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;

    public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    //JWT 토큰 생성 메서드 (인자로 만료 시간, 유저 정보를 받음)
    //set() 계열의 메서드를 통해 여러 값 지정
    private String makeToken(Date expiry, User user) {
        Date now = new Date();

        return Jwts.builder()
                //헤더 타입 : JWT
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                //내용 iss(발급자) : properties 파일에서 설정한 값
                .setIssuer(jwtProperties.getIssuer())
                //내용 iat(발급 일시) : 현재 시간
                .setIssuedAt(now)
                //내용 exp(만료 일시) : expiry 멤버 변숫값
                .setExpiration(expiry)
                //내용 sub(토큰 제목) : 유저 이메일
                .setSubject(user.getEmail())
                //클레임 id : 유저 ID
                .claim("id", user.getId())
                //서명 : 비밀값과 함께 해시값을 HS256 방식으로 암호화
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    //JWT 토큰 유효성 검증 메서드
    //properties 파일에 선언한 비밀값과 함께 토큰 복호화 진행
    public boolean validToken(String token) {
        try {
            Jwts.parser()
                    //비밀값으로 복호화
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            //복호화 과정에서 에러가 나면 유효하지 않은 토큰
            return false;
        }

    }

    //토큰 기반으로 인증 정보를 담은 객체 Authentication 반환하는 메서드
    //클레임 정보 반환받아 사용자 이메일이 들어있는 토큰 제목 sub와 토큰 기바능로 인증 정보 생성
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject
                (), "", authorities), token, authorities);
    }

    //토큰 기반으로 유저 ID 가져오는 메서드
    //비밀값으로 토큰 복호화 한 다음, getClaims() 이용하여 클레임 정보 반환받음
    //반환받은 클레임에서 id 키로 저장된 값을 가져와 반환
    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    //properties 파일에 저장한 비밀 값으로 토큰 복호화한 뒤, 클레임 가져오는 메서드
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}
