package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Article;
import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.exception.NotFountInRepositoryException;
import com.diploma.ticket.system.repository.ArticleRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;


@Service
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final FavorService favorService;

    private static Logger logger= Logger.getLogger(ArticleService.class.getName());
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
            logger.info("Name of article is not correct");
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
            logger.info("Name of article is not correct");
            new IllegalStateException("article whit name " + nameOfTheArticleToUpdate + " does not exist");
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
            logger.warn("Their was no Favor to add to the article ");
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
        logger.info("Article was deleted");
        articleRepository.delete(article);
    }
}
