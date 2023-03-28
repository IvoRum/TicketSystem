package com.diploma.TicketSystem.Ticketing.Ticket;

import com.diploma.TicketSystem.Ticketing.TicketType.TicketType;
import jakarta.persistence.*;

@Entity
@Table(name="Ticket")
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
    private java.lang.String name;//?
    private Long number;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TicketType_id")
    private TicketType type;

    Ticket(){}

    public Ticket(Long id, java.lang.String name, Long number, TicketType type) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.type = type;
    }

    public Ticket(java.lang.String name, Long number, TicketType type) {
        this.name = name;
        this.number = number;
        this.type = type;
    }

    @Override
    public java.lang.String toString() {
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

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
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
