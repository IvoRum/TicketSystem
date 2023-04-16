
import java.util.HashMap;
import java.util.Map;

public class Passenger {

    // unique Passenger ID
    private int id;
    // Map with all attributes
    private Map<String, Integer> attributes;

    //constructor for train data
    public Passenger(String id, String finished,String time,String opendCounters,String activeTickets){
        this(id, time, opendCounters,activeTickets);
        /*0 = not FINISHED, 1 = FINISHED*/
        attributes.put(Attribute.FINISHED, Integer.parseInt(finished));
    }

    //constructor for test data
    public Passenger(String id,String time,String opendCounters,String activeTickets){
        this.id = Integer.parseInt(id);
        attributes = new HashMap<>();
        /*Time Classa, categories: AM=0;"after noon"PM=1  */
        attributes.put(Attribute.TIMEISSUED, time_2_category(time));
        /*Opend counter represent how meny counter wore opent at a time */
        attributes.put(Attribute.OPENDCOUNTERS, Integer.parseInt(opendCounters));
        /*How many tickets ther are in the line whit the person */
        attributes.put(Attribute.ACTIVETICKETS, Integer.parseInt(activeTickets));

    }

    /*
    * Methods for converting numerical data to categorical data
    * */

    private int time_2_category(String time){
        if(time.equals("AM"))return 0;
        if(time.equals("PM"))return 1;
        return 3;
    }

    private int name_2_category(String name){
        if (name.contains("Mr.")) return 0;
        else if (name.contains("Master.")) return 1;
        else if (name.contains("Mrs.") || name.contains("Mme.")) return 2;
        else if (name.contains("Miss.") || name.contains("Mlle.") || name.contains("Ms.")) return 3;
        else return 4;
    }

    /*
    * get the value for a specific attribute
    * */
    public int getAttributeValue(String attribute){
        return attributes.get(attribute);
    }

    public int getId(){
        return this.id;
    }
}