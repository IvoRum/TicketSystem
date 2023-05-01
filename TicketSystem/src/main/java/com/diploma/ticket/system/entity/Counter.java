package com.diploma.ticket.system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Counter")
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer number;
    private boolean active;
    private boolean workingOnClient;
    @ManyToMany
    @JoinTable(
            name = "counter_favor",
            joinColumns = @JoinColumn(name = "counter_id"),
            inverseJoinColumns = @JoinColumn(name = "favor_id"))
    private Set<Favor> favor;

    public void addFavorType(Favor favor) {
        this.favor.add(favor);
    }
}
