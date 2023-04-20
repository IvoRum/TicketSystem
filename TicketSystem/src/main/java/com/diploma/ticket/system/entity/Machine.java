package com.diploma.ticket.system.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Machine {
    @Id
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "favor_type")
    private List<FavorType> favorTypes;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_ticket")
    private List<PersonalTicket> personalTickets;
    private String type;

    public Machine(Long id, String name, List<FavorType> favorTypes, List<PersonalTicket> personalTickets, String type) {
        this.id = id;
        this.name = name;
        this.favorTypes = favorTypes;
        this.personalTickets = personalTickets;
        this.type = type;
    }

    public Machine(String name, List<FavorType> favorTypes, List<PersonalTicket> personalTickets, String type) {
        this.name = name;
        this.favorTypes = favorTypes;
        this.personalTickets = personalTickets;
        this.type = type;
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

    public List<FavorType> getFavorTypes() {
        return favorTypes;
    }

    public void setFavorTypes(List<FavorType> favorTypes) {
        this.favorTypes = favorTypes;
    }

    public List<PersonalTicket> getPersonalTickets() {
        return personalTickets;
    }

    public void setPersonalTickets(List<PersonalTicket> personalTickets) {
        this.personalTickets = personalTickets;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
