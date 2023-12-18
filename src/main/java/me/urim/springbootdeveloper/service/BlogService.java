package me.urim.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.urim.springbootdeveloper.domain.Article;
import me.urim.springbootdeveloper.dto.AddArticleRequest;
import me.urim.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor //final이나 @NotNull이 붙은 필드로 생성자 생성
@Service //빈으로 등록
public class BlogService {
    private final BlogRepository blogRepository;

    //글 추가 method
    public Article save(AddArticleRequest req) {
        // save() : addArticleRequest 클래스에 저장된 값을 article DB에 저장
        return blogRepository.save(req.toEntity());
    }
}
