package com.diploma.ticket.system.repository;

import com.diploma.ticket.system.entity.Article;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfArticleExistsByName() {
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

        underTest.save(article);
        //when

        Optional<Article> articleOptional=underTest.findArticleByName(name);
        boolean expected= articleOptional.isPresent();

        //then
        assertThat(expected).isTrue();
    }
    @Test
    void itShouldCheckIfArticleDoseNotExistsByName() {
        //given
        String name="ArticleNameEx";
        String wrongName="wrongName";
        Article article=new Article(
                1l,
                name,
                "some description",
                new Time(100000l),
                new Time(120000l),
                "Food",
                null
        );

        underTest.save(article);
        //when

        Optional<Article> articleOptional=underTest.findArticleByName(wrongName);
        boolean expected= articleOptional.isPresent();

        //then
        assertThat(expected).isFalse();
    }
}