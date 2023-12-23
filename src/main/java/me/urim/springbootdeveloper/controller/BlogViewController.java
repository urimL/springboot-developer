package me.urim.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.urim.springbootdeveloper.domain.Article;
import me.urim.springbootdeveloper.dto.ArticleListViewResponse;
import me.urim.springbootdeveloper.dto.ArticleViewResponse;
import me.urim.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();

        //블로그 글 리스트 저장
        model.addAttribute("articles", articles);

        //articleList.html 뷰 조회
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article")
    //id를 가진 쿼리 파라미터의 값을 id 변수에 매핑 (id가 없을수도 있다!)
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        //id가 없는 경우 생성해야 된다.
        if (id==null) {
            model.addAttribute("article",new ArticleViewResponse());
        }

        //id가 있는 경우, 수정해준다.
        else {
             Article article = blogService.findById(id);
             model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }

}
