package com.diploma.ticket.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;

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
}
