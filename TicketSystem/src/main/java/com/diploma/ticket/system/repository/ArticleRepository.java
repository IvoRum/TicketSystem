package com.diploma.ticket.system.repository;

import com.diploma.ticket.system.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    @Query("SELECT a FROM Article a WHERE a.name= ?1")
    Optional<Article> findArticleByName(String name);
}
