package me.urim.springbootdeveloper.dto;

import lombok.Getter;
import me.urim.springbootdeveloper.domain.Article;

@Getter
public class ArticleResponse {

    // 글이 제목과 내용으로 구성되므로, 해당 필드를 가지는 클래스를 만든다.
    private final String title;
    private final String content;

    // 엔티티를 인수로 받는 생성자를 추가한다.
    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
