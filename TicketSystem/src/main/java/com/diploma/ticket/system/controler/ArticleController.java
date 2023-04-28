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

        response=articleService.getArticles();

        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity registerNewArticle(@RequestBody Article article){
        articleService.addNewArticle(article);

        return ResponseEntity.ok("Article was created ");
    }

    @PutMapping("/addFavor/{articleId}/{favorId}")
    public ResponseEntity addFavorToExistingArticle(
            @PathVariable Long favorId,
            @PathVariable Long articleId
    ){
        articleService.addFavor(articleId,favorId);

        return ResponseEntity.ok("Favor whit Id:"+favorId+"have been added!");
    }

    @PutMapping(path="{articleName}")
    public ResponseEntity updateArticle(
            @PathVariable("articleId")String name,
            @RequestBody Article article)
    {
       articleService.updateArticle(name, article);

       return  ResponseEntity.ok("Article whit name:"+name+"hase bean updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(
            @PathVariable Long id
    ) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok("Article whit Id:"+id+"is deleted");
    }

}
