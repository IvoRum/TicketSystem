package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Article;
import com.diploma.ticket.system.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void addNewArticle(Article article){
        Optional<Article> articleOptional
                =articleRepository.findArticleByName(article.getName());
        boolean exists=articleOptional.isPresent();
        if(exists){
            throw new IllegalStateException("Name is taken");
        }
        articleRepository.save(article);
    }


    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public void updateArticle(String nameOfTheArticleToUpdate, Article article) {
        String name=article.getName();
        Optional<Article> updatedArticle=articleRepository.findArticleByName(nameOfTheArticleToUpdate);
        boolean exits=updatedArticle.isPresent();
        if(exits) {
            new IllegalStateException("article whit name " + nameOfTheArticleToUpdate + " does not exost");
        }
        if(name!=null
                &&!Objects.equals(article.getName(),name)){
            updatedArticle.get().setName(name);
        }
        articleRepository.save(article);
    }
}
