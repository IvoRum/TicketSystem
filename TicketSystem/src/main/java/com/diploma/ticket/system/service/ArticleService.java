package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Article;
import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.exception.NotFountInRepositoryException;
import com.diploma.ticket.system.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final FavorService favorService;
    @Autowired
    public ArticleService(ArticleRepository articleRepository, FavorService favorService) {
        this.articleRepository = articleRepository;
        this.favorService = favorService;
    }

    public void addNewArticle(Article article){
        Optional<Article> articleOptional
                =articleRepository.findArticleByName(article.getName());
        boolean exists=articleOptional.isPresent();
        if(exists){
            throw new IllegalArgumentException("Name is taken");
        }
        articleRepository.save(article);
    }


    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public void updateArticle(
            String nameOfTheArticleToUpdate,
            Article article
    ) {
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

    public void addFavor(
            Long articleId,
            Long favorId
    ) {
        Favor favor
                = null;
        try {
            favor = favorService.findFavorFromRepository(favorId);
        } catch (NotFountInRepositoryException e) {
            throw new NoSuchElementException();
        }

        Article article
                =articleRepository.findById(articleId).orElseThrow();

        article.addFavor(favor);
        articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        Article article
        =articleRepository.findById(id).orElseThrow();

        articleRepository.delete(article);
    }
}
