package me.urim.springbootdeveloper.repository;

import me.urim.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //email로 사용자 정보 가져옴
    Optional<User> findByEmail(String email);
}
