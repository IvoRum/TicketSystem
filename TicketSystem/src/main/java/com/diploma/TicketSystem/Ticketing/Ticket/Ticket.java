package com.diploma.TicketSystem.Ticketing.Ticket;

import com.diploma.TicketSystem.Ticketing.TicketType.TicketType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;//?
    private Long number;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_type")
    private List<TicketType> type;

    Ticket(){}

    /**
     * Crates a Tickete
     * @param id
     * @param name
     * @param number
     * @param type
     */
    public Ticket(Long id, String name, Long number, List<TicketType> type) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.type = type;
    }

    /**
     * Creates a Ticket
     * @param name
     * @param number
     * @param type
     */
    public Ticket(String name, Long number, List<TicketType> type) {
        this.name = name;
        this.number = number;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
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

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public List<TicketType> getType() {
        return type;
    }

    public void setType(List<TicketType> type) {
        this.type = type;
    }
}
