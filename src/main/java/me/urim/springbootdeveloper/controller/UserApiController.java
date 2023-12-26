package me.urim.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.urim.springbootdeveloper.dto.AddUserRequest;
import me.urim.springbootdeveloper.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest req) {
        userService.save(req); //회원가입 메서드 호출
        return "redirect:/login"; //회원가입 완료된 후 로그인 페이지로 redirect
    }
}
