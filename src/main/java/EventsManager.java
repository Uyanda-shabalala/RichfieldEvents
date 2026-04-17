import java.time.LocalDate;
import java.time.LocalTime;


public interface EventsManager  {



    void showEvents(EventStore tempEve);
    void createEvent(EventStore tempEventStorage, String eventName, LocalDate eventDate, LocalTime eventTime, String eventLocation, int maxParticipants);
    void updateEvent(int eventId, String eventName, LocalTime eventTime, LocalDate eventDate, String eventLocation, EventStore tempEventStore);
    void cancelEvent(int eventId, EventStore tempEventStore);

}

