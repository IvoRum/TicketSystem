package com.diploma.ticket.system.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {


    @Test
    void filterTicketsByFavorTypes() {
        Ticket ticket;
        Ticket ticket1;
        PersonalTicket personalTicket;
        Counter counter;
        //Atributes
        FavorType favorType=new FavorType(1l,"Admin","something1");
        FavorType favorType1=new FavorType(2l,"Rezervacii","somting2");
        List<FavorType> favorTypeList=new ArrayList<>();
        favorTypeList.add(favorType);
        Favor favor=new Favor(1l,"Act za rajdane,brak i smurt","disc",new Time(12345),new Time(5431),favorTypeList,null,null);
        List<FavorType> favorTypeLis1=new ArrayList<>();
        favorTypeLis1.add(favorType1);
        Favor favor1=new Favor(1l,"Balnazali","dis2",new Time(1234),new Time(4321),favorTypeLis1,null,null);

        Set<Favor> favorSet=new HashSet<>();
        favorSet.add(favor);
        Set<Favor> favorSet1=new HashSet<>();
        favorSet1.add(favor1);

        ticket=new Ticket(1l,"act za rajdane",favorSet,null,new LinkedList<>(),new Time(1234),new Time(3421),null);
        ticket1=new Ticket(1l,"Rezervirane na balna zala 1",favorSet1,new LinkedList<>(),null,new Time(1234),new Time(3421),null);

        /*
        LinkedList<PersonalTicket> ticketPersonallTickets=new LinkedList<>();
        personalTicket=new PersonalTicket(1l,true,new Time(1500),new Time(1244));
        //ticket.addPersonalTicket(personalTicket);
        ticketPersonallTickets.add(personalTicket);
        personalTicket=new PersonalTicket(2l,true,new Time(1500),new Time(1255));
        //ticket.addPersonalTicket(personalTicket);
        ticketPersonallTickets.add(personalTicket);
        personalTicket=new PersonalTicket(3l,true,new Time(1500),new Time(1246));
        //ticket.addPersonalTicket(personalTicket);
        //ticketPersonallTickets.add(personalTicket);
        ticket.setPersonalTickets(ticketPersonallTickets);
        LinkedList<PersonalTicket> ticketPersonallTickets1=new LinkedList<>();
        personalTicket=new PersonalTicket(4l,true,new Time(1500),new Time(1266));
        //ticket1.addPersonalTicket(personalTicket);
        ticketPersonallTickets1.add(personalTicket);
        personalTicket=new PersonalTicket(5l,true,new Time(1500),new Time(1277));
        //ticket1.addPersonalTicket(personalTicket);
        ticketPersonallTickets1.add(personalTicket);
        personalTicket=new PersonalTicket(6l,true,new Time(1500),new Time(1288));
        //ticket1.addPersonalTicket(personalTicket);
        ticketPersonallTickets1.add(personalTicket);
        ///
        List<Favor> filters=new ArrayList<>();
        filters.add(favor);
        filters.add(favor1);
        Map<Ticket, LinkedList<PersonalTicket>> ticketMapForTesting=new HashMap<>();
        ticketMapForTesting.put(ticket,ticketPersonallTickets);
        ticketMapForTesting.put(ticket1,ticketPersonallTickets1);

        Queue queue=new Queue(filters,ticketMapForTesting);
        /////Start the test
        //System.out.println(queue.filterTicketsByFavorTypes(favorType));
        //assertEquals(queue.filterTicketsByFavorTypes(favorType), ticketPersonallTickets);
        //assertEquals(queue.filterTicketsByFavorTypes(favorType1), ticketPersonallTickets1);

         */
    }
}