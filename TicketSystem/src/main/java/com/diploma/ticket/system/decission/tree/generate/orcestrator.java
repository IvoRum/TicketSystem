package generate;

import java.util.ArrayList;
import java.util.List;

public class orcestrator {
    public static void main(String[] args){
        CsvWriter write=new CsvWriter();
        List<Ticket> tickets=new ArrayList<>();
        for(int i=0;i<1000;i++){
            tickets.add(write.generateTicketTest());
        }
        write.writeCsvFile("test.csv",tickets);
    }
}
