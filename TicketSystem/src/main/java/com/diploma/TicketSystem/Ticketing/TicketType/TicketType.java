package com.diploma.TicketSystem.Ticketing.TicketType;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "TicketType")
public class TicketType {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Ticket_sequence"
    )
    @SequenceGenerator(
            name="Ticket_sequence",
            sequenceName = "Ticket_sequence",
            allocationSize = 1
    )
    private Long id;
    private String name;
    private String discription;
    private boolean chekedTicket;
    private boolean subTicket;
    private boolean active;

    public TicketType(TicketTypeBuilder ticketTypeBuilder) {
        this.name=ticketTypeBuilder.getName();
        this.discription=ticketTypeBuilder.getDiscription();
        this.chekedTicket=ticketTypeBuilder.isChekedTicket();
        this.subTicket=ticketTypeBuilder.isSubTicket();
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
