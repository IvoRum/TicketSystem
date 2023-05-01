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
@Getter
@Setter
@Entity
@Table(name="Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "favor_tickets",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "favor_id"))
    private Set<Favor> favor;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_type_id")
    private Set<TicketType> type;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="personal_ticket")
    private Set<PersonalTicket> personalTickets;

    @Basic
    @Temporal(TemporalType.TIME)
    private Time workStart;

    @Basic
    @Temporal(TemporalType.TIME)
    private Time workEnd;

    @ManyToMany(targetEntity = User.class, cascade = { CascadeType.ALL })
    @JoinTable(name = "user_ticket",
            joinColumns = { @JoinColumn(name = "ticket_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<User> users=new HashSet<>();

    public void addUser(User user){
        this.users.add(user);
    }

    public void addPersonalTicket(PersonalTicket personalTicket) {
        personalTickets.add(personalTicket);
    }

    public void addFavor(Favor favor) {
        this.favor.add(favor);
    }
}
