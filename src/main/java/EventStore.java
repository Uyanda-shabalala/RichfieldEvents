import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

// in event storage is where we would store/ read data from the text

public class EventStore {
  private final List<Event> events = new ArrayList<>();
  private final Map<Integer, Event> eventsById = new HashMap<>();
  private final List<Student> students = new ArrayList<>();

  public Event findEventById(int id) {

    return eventsById.get(id); // returns the Event object at the position of the ID
  }

  public void addEvent(Event event) {
    events.add(event);
    eventsById.put(event.getEventId(), event);
  }

  public List<Event> getAllEvents() {

    for (Event e : events) {
      System.out.println(e.getEventId() + ". " + e.getEventName());
    }

    return events;
  }

  public void updateEvent(
      int eventId, LocalTime eventTime, String eventLocation, LocalDate eventDate) {

    Event event = eventsById.get(eventId); // map finds event data
    if (event != null) {
      event.setEventTime(eventTime);
      event.setEventLocation(eventLocation);
      event.setEventDate(eventDate);
    } else {
      System.out.println("Event not found.");
    }
  }

  public void cancelEvent(Event event) {

    if (event != null) {
      events.remove(event);
      eventsById.remove(event.getEventId(), event);
      System.out.println("Event cancelled.");
    } else System.out.println("Event not found.");
  }

  public List<Student> getAllStudents() {
    return students;
  }
}
