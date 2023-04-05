package com.diploma.TicketSystem.Ticketing.personalTicket;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name="PersonalTicket")
public class PersonalTicket {
    @Id
    @Column(name="number")
    private Long number;
    private boolean active=true;
    public PersonalTicket(){}

    public PersonalTicket(boolean active) {
        this.active = active;
    }

    public PersonalTicket(Long number, boolean active) {
        this.number = number;
        this.active = active;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "PersonalTicket{" +
                "number=" + number +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalTicket that = (PersonalTicket) o;
        return active == that.active && number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, active);
    }
}
