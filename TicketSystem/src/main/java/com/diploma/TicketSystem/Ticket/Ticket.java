package com.diploma.TicketSystem.Ticket;

import com.diploma.TicketSystem.TicketType.TicketType;
import jakarta.persistence.*;

@Entity
@Table
class Ticket {
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
    private String name;//?
    private Long number;
    private TicketType type;

    Ticket(){}

    public Ticket(Long id, String name, Long number, TicketType type) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.type = type;
    }

    public Ticket(String name, Long number, TicketType type) {
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
                ", type=" + type +
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

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }
}
