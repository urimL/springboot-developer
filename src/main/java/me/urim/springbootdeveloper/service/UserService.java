package me.urim.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.urim.springbootdeveloper.domain.User;
import me.urim.springbootdeveloper.dto.AddUserRequest;
import me.urim.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                //password 암호화
                //PW를 저장할 때, 시큐리티를 설정하며 패스워드 인코딩용으로 등록한 빈 사용해서 암호화한 다음 저장
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
}
