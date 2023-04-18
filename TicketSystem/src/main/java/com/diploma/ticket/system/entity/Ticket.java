package com.diploma.ticket.system.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinColumn(name = "favor")
    Set<Favor> favors;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_type")
    private List<TicketType> type;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="personal_ticket")
    private List<PersonalTicket> personalTickets;
    //TODO add contection to the User class wen it is done
    Ticket(){}

    public Ticket(String name, List<TicketType> type, List<PersonalTicket> personalTickets) {
        this.name = name;
        this.type = type;
        this.personalTickets = personalTickets;
    }



    /**
     * Crates a Tickete
     * @param id
     * @param name
     * @param type
     */
    public Ticket(Long id, String name, List<TicketType> type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    /**
     * Creates a Ticket
     * @param name
     * @param type
     */
    public Ticket(String name, List<TicketType> type) {
        this.name = name;
        this.type = type;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + getType().getClass() +
                '}';
    }

    public List<PersonalTicket> getPersonalTickets() {
        return personalTickets;
    }

    public void setPersonalTickets(List<PersonalTicket> personalTickets) {
        this.personalTickets = personalTickets;
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

    public List<TicketType> getType() {
        return type;
    }

    public void setType(List<TicketType> type) {
        this.type = type;
    }
}
