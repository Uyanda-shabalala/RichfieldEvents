import java.time.LocalDate;
import java.time.LocalTime;

public interface EventsManager {



    public void showEvents();
    public void createEvent(Event event);
    public void updateEvent(int eventId,String eventName,LocalTime eventTime,String eventLocation);
    public void cancelEvent(int eventId,String eventName);



}


