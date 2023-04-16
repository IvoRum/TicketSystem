package generate;

public class Ticket {
    private int id;
    private String time;
    private int opendCounters;
    private int activeTickets;
    private int finished;


    public Ticket(int id,String time, int opendCounters,int activeTickets){
        this.id=id;
        this.time=time;
        this.opendCounters=opendCounters;
        this.activeTickets=activeTickets;
    }
    
    public Ticket(int id,int finished,String time, int opendCounters,int activeTickets){
        this.id=id;
        this.finished=finished;
        this.time=time;
        this.opendCounters=opendCounters;
        this.activeTickets=activeTickets;
    }

    int getFinished(){
        return this.finished;
    }

    int getId(){
        return this.id;
    }
    String getTime(){
        return this.time;
    }
    int getOpendCounter(){
        return this.opendCounters;
    }
    int getActiveTickets(){
        return this.activeTickets;
    }
}
