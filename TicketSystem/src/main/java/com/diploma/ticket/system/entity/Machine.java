package com.diploma.ticket.system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="Machine")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "favor_type")
    private List<FavorType> favorTypes;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_ticket")
    private List<PersonalTicket> personalTickets;


}
