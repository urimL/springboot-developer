package me.urim.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// entity로 지정
@Entity
//getter method
@Getter
//기본 생성자 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동으로 +1
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", updatable = false)
    private String title;

    @Column(name = "content", updatable = false)
    private String content;

    @Builder // builder 패턴으로 객체 생성
    public Article(String title, String content){
        this.title = title;
        this.content = content;
    }

}
