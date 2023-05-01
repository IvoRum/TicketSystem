package com.diploma.ticket.system.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="FavorType")
public class FavorType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToMany
    @JoinTable(
            name = "counter_favor_type",
            joinColumns = @JoinColumn(name = "favor_type_id"),
            inverseJoinColumns = @JoinColumn(name = "counter_id"))
    private Set<Counter> counter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "favor_id")
    private Favor favor;
    //TODO ADD User
    //TODO add Machine


    public FavorType(){}
    public FavorType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public FavorType(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
