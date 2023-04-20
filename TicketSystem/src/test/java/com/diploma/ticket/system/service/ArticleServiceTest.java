package com.diploma.ticket.system.service;

import com.diploma.ticket.system.entity.Article;
import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.entity.Ticket;
import com.diploma.ticket.system.entity.TicketType;
import com.diploma.ticket.system.repository.ArticleRepository;
import com.diploma.ticket.system.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    private ArticleService underTest;
    @BeforeEach
    void setUp(){
        underTest=new ArticleService(articleRepository);
    }


    @Test
    void addNewArticle() {
        //given
        Favor favor=new Favor("Name of the favor","Discription of the favor",
                new Time(10l),new Time(12l));
        Set<Favor> favorSet=new HashSet<>();
        favorSet.add(favor);

        Article article=new Article("Article Name","Some description"
        ,new Time(10l),new Time(12l),"Type of the article",favorSet);
        //when
        underTest.addNewArticle(article);

        //then
        ArgumentCaptor<Article> ticketArgumentCaptor=
                ArgumentCaptor.forClass(Article.class);
        verify(articleRepository).save(ticketArgumentCaptor.capture());

        Article capturedTicket=ticketArgumentCaptor.getValue();
        assertThat(capturedTicket).isEqualTo(article);

    }

    @Test
    void canGetArticles() {
        //when
        underTest.getArticles();
        //then
        verify(articleRepository).findAll();
    }

    @Test
    @Disabled
    void updateArticle() {
    }
}