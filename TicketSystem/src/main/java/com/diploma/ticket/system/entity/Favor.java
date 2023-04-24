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
@Table(name="Service")
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

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="type")
    private List<FavorType> type;

    @ManyToMany
    @JoinColumn(name = "ticket")
    Set<Ticket> tickets;

    @ManyToMany
    @JoinColumn(name = "article")
    Set<Article> article;

}
