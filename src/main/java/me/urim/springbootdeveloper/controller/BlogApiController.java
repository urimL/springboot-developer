package me.urim.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.urim.springbootdeveloper.domain.Article;
import me.urim.springbootdeveloper.dto.AddArticleRequest;
import me.urim.springbootdeveloper.dto.ArticleResponse;
import me.urim.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response body에 객체 데이터를 Json 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest req) {
        Article savedArticle = blogService.save(req);

        // 요청 자원이 성공적으로 생성되었으며, 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        // GET 요청이 오면 글 전체를 조회하는 findAll() 메서드를 호출한다.
        // 응답용 객체인 ArticleResponse로 파싱해 body에 담아 클라이언트에게 전송한다.
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

}
