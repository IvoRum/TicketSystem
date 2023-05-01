package com.diploma.ticket.system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="favor")
public class Favor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Basic
    @Temporal(TemporalType.TIME)
    private Time workStart;
    @Basic
    @Temporal(TemporalType.TIME)
    private Time workEnd;

    @ManyToMany
    @JoinTable(
            name = "favor_tickets",
            joinColumns = @JoinColumn(name = "favor_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    Set<Ticket> ticket;

    @ManyToMany
    @JoinColumn(name = "article")
    Set<Article> article;

    public void addTicket(Ticket ticket) {
        this.ticket.add(ticket);
    }
}
