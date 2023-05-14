package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Article;
import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.exception.NotFountInRepositoryException;
import com.diploma.ticket.system.repository.ArticleRepository;
import com.diploma.ticket.system.repository.FavorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.Optional;
import java.util.TreeSet;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private FavorRepository favorRepository;
    private ArticleService underTest;
    private FavorService favorService;


    @BeforeEach
    void setUp() {
        favorService=new FavorService(favorRepository);
        underTest=new ArticleService(articleRepository,favorService);
    }


    @Test
    void canAddNewArticle() {
        //given
        String name="ArticleNameEx";
        Article article=new Article(
                1l,
                name,
                "some description",
                new Time(100000l),
                new Time(120000l),
                "Food",
                null
        );
        //when
        underTest.addNewArticle(article);
        //then
        ArgumentCaptor<Article> argumentCaptor
                =ArgumentCaptor.forClass(Article.class);
        verify(articleRepository).save(argumentCaptor.capture());

        Article captorArticle=argumentCaptor.getValue();

        assertThat(captorArticle).isEqualTo(article);
    }

    @Test
    void wontAddNewArticleNameTaken() {
        //given
        String name="ArticleNameEx";
        Article article=new Article(
                1l,
                name,
                "some description",
                new Time(100000l),
                new Time(120000l),
                "Food",
                null
        );
        Optional<Article> optionalArticle= Optional.of(article);
        given(articleRepository.findArticleByName(anyString()))
                .willReturn(optionalArticle);
        //when
        assertThatThrownBy(()->underTest.addNewArticle(article))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Name is taken");

    }


    @Test
    void getArticles() {
        //when
        underTest.getArticles();
        //then
        verify(articleRepository).findAll();
    }

    @Disabled
    @Test
    void updateArticle() {
    }

    @Test
    @Disabled
    void addFavor()  {
        //given
        Long id=1l;
        Favor favor=Favor.builder().build();
        favor.setId(id);


        String name="ArticleNameEx";
        Article article=Article.builder().build();
        article.setId(id);
        article.setFavors(new TreeSet<>());
        //when

        Optional<Favor> optionalFavor= Optional.of(favor);
        Optional<Article> optionalArticle= Optional.of(article);
        given(favorRepository.findById(id))
                .willReturn(optionalFavor);
        given(articleRepository.findById(id))
                .willReturn(optionalArticle);
        underTest.addFavor(id,article.getId());

        //then
        //articleRepository.findByI
        ArgumentCaptor<Article> argumentCaptor
                =ArgumentCaptor.forClass(Article.class);
        verify(articleRepository).save(argumentCaptor.capture());

        Article captorArticle=argumentCaptor.getValue();

        assertThat(captorArticle).isEqualTo(article);
    }

    @Test
    void deleteArticle() {
        //when
        Long id=1l;
        Article article=Article.builder().build();
        article.setId(id);
        Optional<Article> articleOptional= Optional.of(article);
        given(articleRepository.findById(id))
                .willReturn(articleOptional);
        underTest.deleteArticle(article.getId());
        //then
        verify(articleRepository).delete(article);
    }
}