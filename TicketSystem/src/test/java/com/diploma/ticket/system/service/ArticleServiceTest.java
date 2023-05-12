package com.diploma.ticket.system.service;

import com.diploma.ticket.system.repository.ArticleRepository;
import com.diploma.ticket.system.repository.FavorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private FavorRepository favorRepository;
    private ArticleService underTest;
    private FavorService favorService;

    AutoCloseable autoCloseableArticle;
    AutoCloseable autoCloseableFavor;

    @BeforeEach
    void setUp() {
        autoCloseableArticle=MockitoAnnotations.openMocks(this);
        autoCloseableFavor=MockitoAnnotations.openMocks(this);
        favorService=new FavorService(favorRepository);
        underTest=new ArticleService(articleRepository,favorService);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseableFavor.close();
        autoCloseableArticle.close();

    }

    @Test
    void addNewArticle() {
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
    void addFavor() {
    }

    @Test
    void deleteArticle() {
    }
}