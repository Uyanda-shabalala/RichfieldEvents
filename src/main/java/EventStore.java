import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

// in event storage is where we would store/ read data from the text

public class EventStore {
  private final ArrayList<Event> events = new ArrayList<>();
  private final Map<Integer, Event> eventsById = new HashMap<>();
  private final List<Student> students = new ArrayList<>();
  private final List<Staff> staff = new ArrayList<>();

  public Boolean doesUserExist(String name) {
    for (Staff staff1 : this.staff) {
      if (staff1.getName().equalsIgnoreCase(name)) {
        return true;
      }
    }
    return false;
  }

  public Event findEventById(int id) {

    return eventsById.get(id); // returns the Event object at the position of the ID
  }

  public void addEvent(Event event) {
    events.add(event);
    eventsById.put(event.getEventId(), event);
  }

  public ArrayList<Event> getAllEvents() {

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

  public void addStudent(Student student) {
    students.add(student);
  }

  public List<Staff> getAllStaff() {
    return staff;
  }

  public void addStaff(Staff staffMember) {
    staff.add(staffMember);
  }

  public List<Event> search(String eventName) {
    eventName = eventName.toLowerCase();
    List<Event> eventsMatchingSearch = new ArrayList<>();

    for (Event event : events) {
      if (event.getEventName().toLowerCase().contains(eventName)) {
        eventsMatchingSearch.add(event);
      }
    }
    return eventsMatchingSearch;
  }
}
