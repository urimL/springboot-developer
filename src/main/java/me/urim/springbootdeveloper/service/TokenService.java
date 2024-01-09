package me.urim.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.urim.springbootdeveloper.config.jwt.TokenProvider;
import me.urim.springbootdeveloper.domain.User;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    //전달받은 리프레시 토큰으로 유효성 검사 하고, 유효한 토큰일 경우 리프레시 토큰으로 사용자 아이디를 찾는 메서드
    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
