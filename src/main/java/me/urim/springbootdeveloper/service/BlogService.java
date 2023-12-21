package me.urim.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.urim.springbootdeveloper.domain.Article;
import me.urim.springbootdeveloper.dto.AddArticleRequest;
import me.urim.springbootdeveloper.dto.UpdateArticleRequest;
import me.urim.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor //final이나 @NotNull이 붙은 필드로 생성자 생성
@Service //빈으로 등록
public class BlogService {
    private final BlogRepository blogRepository;

    //글 추가 method
    public Article save(AddArticleRequest req) {
        // save() : addArticleRequest 클래스에 저장된 값을 article DB에 저장
        return blogRepository.save(req.toEntity());
    }

    //글 모두 가져오기
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    //게시글 조회
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    //글 삭제
    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    //글 수정
    //@Transactional : 매칭한 메서드를 하나의 트랜잭션으로 묶는 역할
    //이 메서드는 엔티티의 필드 값이 바뀌면 에러가 발생해도 제대로 된 값 수정 보장받음
    @Transactional
    public Article update(long id, UpdateArticleRequest req) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: "+id));

        article.update(req.getTitle(),req.getContent());

        return article;
    }

}
