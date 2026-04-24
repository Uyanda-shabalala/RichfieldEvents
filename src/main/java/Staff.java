import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Staff extends User implements EventsManager {
  final String role = "Staff";

  public Staff(EventStore eventStore, String name, String email, String role) {
    super(name, email);
    addStaffMember(eventStore, this);
  }

  public Staff(EventStore eventStore, String name) {
    super();
    this.name = name;
  }

  public static void addStaffMember(EventStore eventStore, Staff staff) {
    if (eventStore.getAllStaff().contains(staff)) {
      System.out.println("Staff Member already exists");
    } else {
      eventStore.addStaff(staff);
    }
  }

  @Override
  public String getRole() {
    return role;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name.toLowerCase(Locale.ROOT);
  }

  public String getEmail() {
    return email;
  }

  // Logic

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public void showEvents(EventStore tempEventStore) {
    for (Event event : tempEventStore.getAllEvents()) {
      System.out.println(
          event.getEventId()
              + event.getEventName()
              + event.getEventTime()
              + event.getEventLocation());
    }
  }

  @Override
  public void createEvent(
      EventStore tempEventStorage,
      String eventName,
      LocalDate eventDate,
      LocalTime eventTime,
      String eventLocation,
      int maxParticipants) {

    if (eventName.isEmpty() || eventLocation.isEmpty()) {
      System.out.println("Fields cannot be empty.");
      return;
    }

    if (maxParticipants <= 0) {
      System.out.println("Max participants must be greater than 0.");
      return;
    }

    Event event = new Event(eventName, eventDate, eventTime, eventLocation, maxParticipants);
    if (tempEventStorage.findEventById(event.getEventId()) != null) {
      System.out.println("Event ID already exists.");
      return;
    }
    if (tempEventStorage.getAllEvents().contains(event)) {
      System.out.println("Event already exists");
    } else {

      tempEventStorage.addEvent(event);
    }
  }

  public void updateEvent(
      int eventId,
      String eventName,
      LocalTime eventTime,
      LocalDate eventDate,
      String eventLocation,
      EventStore tempEventStore) {
    Event event = tempEventStore.findEventById(eventId);
    if (event == null) {
      System.out.println("Event not found. Please check the ID and try again.");
      return;
    }

    tempEventStore.updateEvent(eventId, eventTime, eventLocation, eventDate);
  }

  @Override
  public void cancelEvent(int eventId, EventStore tempEventStore) {
    Event event = tempEventStore.findEventById(eventId);
    if (event == null) {
      System.out.println("Event not found. Please check the ID and try again.");
      return;
    }
    tempEventStore.cancelEvent(event);
  }

  public void viewParticipants(EventStore tempEventStore) {
    for (Event event : tempEventStore.getAllEvents()) {
      System.out.println("Event: " + event.getEventName());
      System.out.println("Registered Students:");
      for (Student s : event.getRegisteredStudents()) {
        System.out.println("  - " + s.getName());
      }
      System.out.println("Waitlist:");
      for (Student s : event.getWaitlistedStudents()) {
        System.out.println("  - " + s.getName());
      }
      System.out.println("--------------------");
    }
  }

  public void sortEvents(EventStore tempEventStore) {

    System.out.println("Do you wish to sort by 1.Event Name or 2.Dates");
    System.out.println("1.Event Name or 2.Dates");
    Scanner sc = new Scanner(System.in);
    String choice = sc.nextLine();

    if (choice.equalsIgnoreCase("1")) {
      Collections.sort(tempEventStore.getAllEvents(), Comparator.comparing(Event::getEventName));

    } else if (choice.equalsIgnoreCase("2"))
      Collections.sort(tempEventStore.getAllEvents(), Comparator.comparing(Event::getEventDate));
  }
}
