package com.diploma.TicketSystem.Ticketing.TicketType;

public class TicketTypeBuilder {
    private String name;
    private String discription;
    private boolean chekedTicket;
    private boolean subTicket;

    public TicketType build(){
        return new TicketType(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public boolean isChekedTicket() {
        return chekedTicket;
    }

    public void setChekedTicket(boolean chekedTicket) {
        this.chekedTicket = chekedTicket;
    }

    public boolean isSubTicket() {
        return subTicket;
    }

    public void setSubTicket(boolean subTicket) {
        this.subTicket = subTicket;
    }
}
