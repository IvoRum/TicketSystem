package com.diploma.ticket.system.controler;

import com.diploma.ticket.system.entity.Article;
import com.diploma.ticket.system.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="api/v1/article")
public class ArticleControler  {
    private final ArticleService articleService;
    @Autowired
    public ArticleControler(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    private List<Article> getArticle(){
        return articleService.getArticles();
    }

    @PostMapping
    public void registerNewArticle(@RequestBody Article article){
        articleService.addNewArticle(article);
    }

    @PutMapping(path="{articleId}")
    public void updateArticle(@PathVariable("articleName")String name,
                              @RequestBody Article article){
        articleService.updateArticle(name,article);
    }

}
