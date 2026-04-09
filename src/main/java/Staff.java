import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Staff extends User implements EventsManager {
    final String role = "Staff";

    public Staff(String name, String email, String role) {
        super(name, email);
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


    //Logic

    @Override
    public void showEvents(EventStore tempEventStore) {
            tempEventStore.getAllEvents();

    }

    @Override
    public void createEvent(EventStore tempEventStorage,int eventId, String eventName, LocalDate eventDate, LocalTime eventTime, String eventLocation, int maxParticipants) {
        Event event = new Event(eventId, eventName, eventDate, eventTime, eventLocation, maxParticipants);
        tempEventStorage.addEvent(event);
    }

    public void updateEvent(int eventId, String eventName, LocalTime eventTime,LocalDate eventDate, String eventLocation,EventStore tempEventStore) {
         Event event= tempEventStore.findEventById(eventId);

         tempEventStore.updateEvent(eventId,eventTime,eventLocation,eventDate);

    }

    @Override
    public void cancelEvent(int eventId,EventStore tempEventStore) {
        Event event = tempEventStore.findEventById(eventId);
        tempEventStore.cancelEvent(event);

    }

    public void sortEvents(EventStore tempEventStore) {
        List<Event> events=tempEventStore.getAllEvents();


    }




}
