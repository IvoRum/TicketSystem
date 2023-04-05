package com.diploma.TicketSystem.conuter;

import jakarta.persistence.*;

@Entity
@Table(name="Counter")
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    //TODO add List of Service_Type that t counter will sevice
    public Counter(){}

    public Counter(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Counter(String name, String description) {
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
