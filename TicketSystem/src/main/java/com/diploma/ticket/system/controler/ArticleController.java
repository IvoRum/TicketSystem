package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.entity.Article;
import com.diploma.ticket.system.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="api/v2/article")
public class ArticleController {
    private final ArticleService articleService;
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    private ResponseEntity<List<Article>> getArticle(){
        List<Article> response=new ArrayList<>();
        try {
            response=articleService.getArticles();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity registerNewArticle(@RequestBody Article article){
        try {
            articleService.addNewArticle(article);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Article was created ");
    }

    @PutMapping(path="{articleId}")
    public void updateArticle(@PathVariable("articleName")String name,
                              @RequestBody Article article){
        articleService.updateArticle(name,article);
    }

}
