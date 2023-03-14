package com.diploma.TicketSystem.TicketType;

import java.util.List;

public interface TicketType {
    /**
     * To be used to set a Ticket as checked and return
     * @return
     */
    public String CheckNumber();
    public boolean isChecked();
    public List getFullStats();
    public List getStatistics();


}
