package com.diploma.TicketSystem.servicing.serviceType;

import com.diploma.TicketSystem.user.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="ServiceType")
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public ServiceType(){}
    public ServiceType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ServiceType(Long id, String name, String description) {
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
