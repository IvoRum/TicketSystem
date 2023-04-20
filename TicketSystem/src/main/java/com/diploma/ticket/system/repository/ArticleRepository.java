package com.diploma.ticket.system.repository;

import com.diploma.ticket.system.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
    @Query("SELECT a FROM Article a WHERE a.name= ?1")
    Optional<Article> findArticleByName(String name);
}
