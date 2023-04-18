package com.diploma.ticket.system.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="Counter")
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer number;

    @ManyToMany
    @JoinColumn(name = "fovor_type")
    private Set<FavorType> favorType;
    public Counter(){}

    public Counter(Long id, String name, String description, Integer number, Set<FavorType> favorType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.number = number;
        this.favorType = favorType;
    }

    public Counter(String name, String description, Integer number, Set<FavorType> favorType) {
        this.name = name;
        this.description = description;
        this.number = number;
        this.favorType = favorType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Set<FavorType> getFavorType() {
        return favorType;
    }

    public void setFavorType(Set<FavorType> favorType) {
        this.favorType = favorType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
