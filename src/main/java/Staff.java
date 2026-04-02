import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Staff extends User implements EventsManager{
    final String role="Staff";
    private List<Event> events= new ArrayList<>();// list that stores event objects
    private Map<Integer,Event> eventsById = new HashMap<>(); //hashmap to look up events by their event ID
    private String name;
    private  String email;

    public Staff(String name, String email, String role) {
        super(name, email);


    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }

   @Override
   public void showEvents(){

       for (Event event : events) {
           System.out.println(event);
       }

   }

    @Override
    public void createEvent(Event event){
            this.events.add(event);
            this.eventsById.put(event.getEventId(),event);

    }

    @Override
    public void updateEvent(int eventId,String eventName,LocalTime eventTime,String eventLocation){
            Event event=eventsById.get(eventId);
            if (event!=null){
                event.setEventName(eventName);

            }
    }

    @Override
    public void cancelEvent(int eventId,String eventName){


    }



}
