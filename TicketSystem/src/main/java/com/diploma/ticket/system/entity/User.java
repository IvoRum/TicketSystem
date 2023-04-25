package com.diploma.ticket.system.entity;

import com.diploma.ticket.system.type.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Integer id;
    private String firstname;
    private String lastname;
    @NotBlank
    @Size(min = 3, max = 20)
    private String email;
    @NotBlank
    @Size(max = 120)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="personal_ticket")
    private List<PersonalTicket> personalTickets;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="counter")
    private List<Counter> counters;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="service_type")
    private List<FavorType> favorTypes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public void addPersonalTicket(PersonalTicket personalTicket) {
        this.personalTickets.add(personalTicket);
    }
}