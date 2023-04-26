package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.entity.Article;
import com.diploma.ticket.system.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
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

    @PostMapping("/addFavor/{articleId}/{favorId}")
    public ResponseEntity addFavorToExistingArticle(
            @PathVariable Long favorId,
            @PathVariable Long articleId
    ){
        try {
            articleService.addFavor(articleId,favorId);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Favor whit Id:"+favorId+"have been added!");
    }

    @PutMapping(path="{articleId}")
    public ResponseEntity updateArticle(@PathVariable("articleName")String name,
                              @RequestBody Article article){
        try {
            articleService.updateArticle(name, article);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok("Article has been updated");
    }

}
