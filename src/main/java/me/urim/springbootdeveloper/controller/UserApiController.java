package me.urim.springbootdeveloper.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.urim.springbootdeveloper.dto.AddUserRequest;
import me.urim.springbootdeveloper.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

    //회원가입
    @PostMapping("/user")
    public String signup(AddUserRequest req) {
        userService.save(req); //회원가입 메서드 호출
        return "redirect:/login"; //회원가입 완료된 후 로그인 페이지로 redirect
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp) {
        new SecurityContextLogoutHandler().logout(req, resp,
                SecurityContextHolder.getContext().getAuthentication());

        return "redirect:/login";
    }
}
