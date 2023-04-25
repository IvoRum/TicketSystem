package com.diploma.ticket.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name="number")
    private Long number;
    private boolean active=true;
    @Column(name="finish_time")
    private Time finishTime;
    @Column(name="issue_time")
    private Time issueTime;
}
