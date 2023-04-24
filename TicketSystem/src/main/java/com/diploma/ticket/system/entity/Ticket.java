package com.diploma.ticket.system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.List;
import java.util.Set;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinColumn(name = "favor")
    private Set<Favor> favors;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_type")
    private List<TicketType> type;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="personal_ticket")
    private List<PersonalTicket> personalTickets;

    @Basic
    @Temporal(TemporalType.TIME)
    private Time workStart;

    @Basic
    @Temporal(TemporalType.TIME)
    private Time workEnd;

    @ManyToMany
    @JoinColumn(name = "user")
    private Set<User> users;
    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + getType().getClass() +
                '}';
    }


}
