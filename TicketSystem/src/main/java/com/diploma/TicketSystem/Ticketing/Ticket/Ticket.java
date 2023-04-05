package com.diploma.TicketSystem.Ticketing.Ticket;

import com.diploma.TicketSystem.Ticketing.TicketType.TicketType;
import com.diploma.TicketSystem.Ticketing.personalTicket.PersonalTicket;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //TODO add a conection to the Personal ticket tale
    

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_type")
    private List<TicketType> type;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="PersonalTicket")
    private List<PersonalTicket> personalTickets;

    Ticket(){}

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
