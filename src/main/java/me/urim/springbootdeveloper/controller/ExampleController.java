package me.urim.springbootdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExampleController {

    @GetMapping("/thymeleaf/example")
    //뷰로 데이터를 넘겨주는 model 객체 사용
    public String thymeleafExample(Model model) {
        Person examplePerson = new Person();
        examplePerson.setId(1L);
        examplePerson.setName("김바덕");
        examplePerson.setAge(11);
        examplePerson.setHobbies(List.of("운동","독서"));

        model.addAttribute("person", examplePerson);
        model.addAttribute("today", LocalDate.now());

        //@Controller가 있으므로 view의 이름 반환
        return "example";
    }
}

@Setter
@Getter
class Person {
    private Long id;
    private String name;
    private int age;
    private List<String> hobbies;
}