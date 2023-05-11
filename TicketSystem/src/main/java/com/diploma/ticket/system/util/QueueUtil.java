package com.diploma.ticket.system.util;

import com.diploma.ticket.system.entity.Favor;
import com.diploma.ticket.system.entity.PersonalTicket;
import com.diploma.ticket.system.entity.Ticket;
import com.diploma.ticket.system.entity.User;
import com.diploma.ticket.system.service.TicketService;
import com.diploma.ticket.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Component
public class QueueUtil {

    private final UserService userService;
    private Logger logger=Logger.getLogger(QueueUtil .class.getName());
    private final TicketService ticketService;

    @Autowired
    public QueueUtil(UserService userService, TicketService ticketService) {
        this.userService = userService;
        this.ticketService = ticketService;
    }

    public User getUserFromToken(String userEmail){
        User user=userService.findUserByEmail(userEmail);
        logger.info("Get user from Token was invoked");
        return user;
    }
    public List<List<Ticket>> getTicketFromFavors(Set<Favor> favors){
        List<List<Ticket>> ticketInLine=new ArrayList<>();
        for(Favor favor:favors){
            ticketInLine.add(ticketService.findTicketByFavor(favor.getId()));
        }
        logger.info("Get Ticket from Favor was invoked");
        return ticketInLine;
    }

    public List<Set<PersonalTicket>> getPersonalTicketsFromTicket(List<List<Ticket>> ticketInLine)  {
        List<Set<PersonalTicket>> personalTickets = new ArrayList<>();
        for (List<Ticket> ticketList : ticketInLine) {
            for (Ticket ticket : ticketList) {
                personalTickets.add(ticket.getPersonalTickets());
            }
        }
        return personalTickets;
    }

    /**
     * The class is used for the way in witch the line filters out the next ticket
     * @param <T> The set of Personal Tickets
     */
    public static class SetInListIterator<T> implements Iterator<T> {

        private final List<Set<T>> list;
        private int currentListIndex;
        private Iterator<T> currentSetIterator;

        public SetInListIterator(List<Set<T>> list) {
            this.list = list;
            this.currentListIndex = 0;
            this.currentSetIterator = list.get(0).iterator();
        }

        @Override
        public boolean hasNext() {
            if (currentSetIterator.hasNext()) {
                return true;
            } else if (currentListIndex < list.size() - 1) {
                currentListIndex++;
                currentSetIterator = list.get(currentListIndex).iterator();
                return hasNext();
            } else {
                return false;
            }
        }

        @Override
        public T next() {
            if (hasNext()) {
                return currentSetIterator.next();
            } else {
                throw new java.util.NoSuchElementException();
            }
        }

        public Iterator<T> getCurrent(){
            return currentSetIterator;
        }
    }
}
