package com.diploma.TicketSystem.Ticketing.TicketType;

public class TicketTypeBuilder {
    private String name="";
    private String discription="";
    private boolean chekedTicket=false;
    private boolean subTicket=false;

    public TicketTypeBuilder(String name, String discription, boolean chekedTicket, boolean subTicket) {
        this.name = name;
        this.discription = discription;
        this.chekedTicket = chekedTicket;
        this.subTicket = subTicket;
    }

    public TicketTypeBuilder() {}

    public TicketType build(){
        return new TicketType(this.name,this.discription,chekedTicket,subTicket,true);
    }

    public String getName() {
        return name;
    }

    public TicketTypeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public String getDiscription() {
        return discription;
    }

    public TicketTypeBuilder setDiscription(String discription) {
        this.discription = discription;
        return this;
    }

    public boolean isChekedTicket() {
        return chekedTicket;
    }

    public TicketTypeBuilder setChekedTicket(boolean chekedTicket) {
        this.chekedTicket = chekedTicket;
        return this;
    }

    public boolean isSubTicket() {
        return subTicket;
    }

    public TicketTypeBuilder setSubTicket(boolean subTicket) {
        this.subTicket = subTicket;
        return this;
    }
}
