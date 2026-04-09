import java.time.LocalDate;
import java.time.LocalTime;

public interface EventsManager  {



    public void showEvents(EventStore tempEve);
    public void createEvent(EventStore tempEventStorage,int eventId, String eventName, LocalDate eventDate, LocalTime eventTime, String eventLocation, int maxParticipants);
    public void updateEvent(int eventId, String eventName, LocalTime eventTime,LocalDate eventDate, String eventLocation,EventStore tempEventStore);
    public void cancelEvent(int eventId,EventStore tempEventStore);

}

