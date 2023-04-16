package generate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class CsvWriter {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "id,time,opendCounters,activeTickets";
    private int id=0;

    public static void writeCsvFile(String fileName, List<Ticket> tickets) {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);

            // Write the CSV file header
            fileWriter.append(FILE_HEADER.toString());

            // Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            for (Ticket i : tickets) {
                fileWriter.append(String.valueOf(i.getId()));
                fileWriter.append(COMMA_DELIMITER);
               // fileWriter.append(String.valueOf(i.getFinished()));
                //fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(i.getTime());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(i.getOpendCounter()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(i.getActiveTickets()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

            System.out.println("CSV file was created successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }
    }

    public Ticket generateTicketTest(){
        int min = 1;
        int max = 10;
        Random ram=new Random();
        id=id+1;
        String time;
        if(ram.nextInt()%2==0){
            time="AM";
        }else time="PM";

        int opendCounters = ram.nextInt((max - min) + 1) + min;
        min=0;
        max=25;
        int activeTickets = ram.nextInt((max - min) + 1) + min;

        Ticket ticket=new Ticket(id,time,opendCounters, activeTickets);
        return ticket;
    }

    
    public Ticket generateTicketTrain(){
        int min = 1;
        int max = 10;
        Random ram=new Random();
        id=id+1;
        String time;
        if(ram.nextInt()%2==0){
            time="AM";
        }else time="PM";

        int opendCounters = ram.nextInt((max - min) + 1) + min;
        min=0;
        max=25;
        int activeTickets = ram.nextInt((max - min) + 1) + min;
        int finished=0;
        if(ram.nextInt()%2==0){
            finished=1;
        }else finished=0;

        Ticket ticket=new Ticket(id,finished,time,opendCounters, activeTickets);
        return ticket;
    }
}
