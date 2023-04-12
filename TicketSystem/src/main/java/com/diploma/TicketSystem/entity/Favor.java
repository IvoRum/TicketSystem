package com.diploma.TicketSystem.entity;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name="Service")
public class Favor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Time workStart;
    private Time workEnd;
    //TODO add Service type wen it is ready


    public Favor(Long id, String name, String description, Time workStart, Time workEnd) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.workStart = workStart;
        this.workEnd = workEnd;

    }

    public Favor(String name, String description, Time workStart, Time workEnd) {
        this.name = name;
        this.description = description;
        this.workStart = workStart;
        this.workEnd = workEnd;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", workStart=" + workStart +
                ", workEnd=" + workEnd +
                '}';
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
