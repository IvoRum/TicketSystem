package com.diploma.TicketSystem.Ticketing.TicketType;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "ticket_type")
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String discription;
    /**
     * This ticket opttion represents if the ticket hase
     * a check and uncheked option to fish of the ticket
     */
    private boolean chekedTicket;
    /**
     * Ticket option that represents if the thicket has
     * any sub articles
     */
    private boolean subTicket;
    /**
     * Shows if the ticket is active or not
     */
    private boolean active;

    public TicketType(String name, String discription, boolean chekedTicket, boolean subTicket, boolean active) {
        this.name = name;
        this.discription = discription;
        this.chekedTicket = chekedTicket;
        this.subTicket = subTicket;
        this.active = active;
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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public boolean isChekedTicket() {
        return chekedTicket;
    }

    public void setChekedTicket(boolean chekedTicket) {
        this.chekedTicket = chekedTicket;
    }

    public boolean isSubTicket() {
        return subTicket;
    }

    public void setSubTicket(boolean subTicket) {
        this.subTicket = subTicket;
    }
}
