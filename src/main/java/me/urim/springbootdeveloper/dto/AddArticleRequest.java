package me.urim.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.urim.springbootdeveloper.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    // 생성자 사용하여 객체 생성
    // toEntity() : 빌더 패터 이용하여 DTO를 엔티티로 만들어준다.
    // 추후 글 추가할 때, 저장할 엔티티로 변환하는 용도
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }

}
