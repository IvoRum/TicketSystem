package com.diploma.TicketSystem.servicing.article;

import jakarta.persistence.*;

import java.sql.Time;


@Entity
@Table(name="Article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Time workStart;
    private Time workEnd;
    //TODO add Article type when it is ready

    public Article(){}
    public Article(String description, Time workStart, Time workEnd) {
        this.description = description;
        this.workStart = workStart;
        this.workEnd = workEnd;
    }

    public Article(Long id, String description, Time workStart, Time workEnd) {
        this.id = id;
        this.description = description;
        this.workStart = workStart;
        this.workEnd = workEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getWorkStart() {
        return workStart;
    }

    public void setWorkStart(Time workStart) {
        this.workStart = workStart;
    }

    public Time getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(Time workEnd) {
        this.workEnd = workEnd;
    }
}
