package com.diploma.ticket.system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="Article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Time workStart;
    private Time workEnd;
    private String type;
    @ManyToMany
    @JoinColumn(name = "favor")
    Set<Favor> favors;
}
