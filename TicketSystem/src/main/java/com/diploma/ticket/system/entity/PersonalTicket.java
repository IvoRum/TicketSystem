package com.diploma.ticket.system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="PersonalTicket")
public class PersonalTicket {
    @Id
    private Long id;
    private boolean active=true;
    @Column(name="finish_time")
    private Time finishTime;
    @Column(name="issue_time")
    private Time issueTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public void addTicket(Ticket ticket) {
        this.ticket=ticket;
    }
}
