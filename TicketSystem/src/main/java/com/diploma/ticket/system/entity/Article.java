package com.diploma.ticket.system.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Set;


@Entity
@Table(name="Article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Time workStart;
    private Time workEnd;
    private String type;
    @ManyToMany
    @JoinColumn(name = "favor")
    Set<Favor> favors;
    public Article(){}
    public Article(String description, Time workStart, Time workEnd) {
        this.description = description;
        this.workStart = workStart;
        this.workEnd = workEnd;
    }

    public Article(String name, String description, Time workStart, Time workEnd, String type, Set<Favor> favors) {
        this.name = name;
        this.description = description;
        this.workStart = workStart;
        this.workEnd = workEnd;
        this.type = type;
        this.favors = favors;
    }

    public Article(String name, String description, Time workStart, Time workEnd, String type) {
        this.name = name;
        this.description = description;
        this.workStart = workStart;
        this.workEnd = workEnd;
        this.type = type;
    }

    public Article(Long id, String name, String description, Time workStart, Time workEnd, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.workStart = workStart;
        this.workEnd = workEnd;
        this.type = type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
