import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Event {

  private static int counter = 0;
  private final int eventId;
  private final Queue<Student> waitlistedStudents = new LinkedList<>();
  private final List<Student> registeredStudents = new ArrayList<>();
  private String eventName;
  private LocalTime eventTime;
  private String eventLocation;
  private LocalDate eventDate;
  private int maxParticipants;

  // constructor

  public Event(
      String eventName,
      LocalDate eventDate,
      LocalTime eventTime,
      String eventLocation,
      int maxParticipants) {
    this.eventId = ++counter;
    this.eventName = eventName;
    this.eventDate = eventDate;
    this.eventTime = eventTime;
    this.eventLocation = eventLocation;
    this.maxParticipants = maxParticipants;
  }

  public Event(
      int eventId,
      String eventName,
      LocalDate eventDate,
      LocalTime eventTime,
      String eventLocation,
      int maxParticipants) {
    this.eventId = eventId;
    this.eventName = eventName;
    this.eventDate = eventDate;
    this.eventTime = eventTime;
    this.eventLocation = eventLocation;
    this.maxParticipants = maxParticipants;
    counter = eventId;
  }

  // getters and setters
  public int getEventId() {
    return eventId;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName.toLowerCase(Locale.ROOT);
  }

  public LocalTime getEventTime() {

    return eventTime;
  }

  public void setEventTime(LocalTime eventTime) {

    this.eventTime = eventTime;
  }

  public String getEventLocation() {

    return eventLocation;
  }

  public void setEventLocation(String eventLocation) {
    this.eventLocation = eventLocation.toLowerCase();
  }

  public LocalDate getEventDate() {
    return eventDate;
  }

  public void setEventDate(LocalDate eventDate) {

    this.eventDate = eventDate;
  }

  public int getMaxParticipants() {
    return maxParticipants;
  }

  public void setMaxParticipants(int maxParticipants) {

    this.maxParticipants = maxParticipants;
  }

  public Queue<Student> getWaitlistedStudents() {
    return waitlistedStudents;
  }

  public List<Student> getRegisteredStudents() {
    return registeredStudents;
  }

  // logic

  public boolean registerStudent(Student student) {

    if (registeredStudents.size() >= maxParticipants) {
      System.out.println("Event is full You will be added to the waitlisted students .");
      waitlistedStudents.add(student);
      return false;
    }

    if (registeredStudents.contains(student)) {
      System.out.println("Already registered.");
      return false;
    }

    registeredStudents.add(student);

    return true;
  }

  public boolean deregisterStudentFromEvent(Student student) {

    boolean removed = registeredStudents.remove(student);

    if (removed) {
      System.out.println(student.getName() + " deregistered from " + eventName);

      // If there are students on the waitlist, promote one
      if (!waitlistedStudents.isEmpty()) {

        Student promotedStudent = waitlistedStudents.poll(); // remove first in queue

        // Run promotion in a separate thread
        Thread promotionThread =
            new Thread(
                () -> {
                  try {
                    Thread.sleep(1000); // simulate background processing
                  } catch (InterruptedException e) {
                    System.out.println("Thread interrupted");
                  }

                  registeredStudents.add(promotedStudent);
                  promotedStudent.getRegisteredEvents().add(this);

                  System.out.println(
                      "Registration cancelled. Student "
                          + promotedStudent.getStudentNumber()
                          + " has been promoted from the waitlist to the event: "
                          + eventName);
                });

        promotionThread.start();
      }
    }

    return removed;
  }

  @Override
  public String toString() {
    return "ID: "
        + eventId
        + " | Name: "
        + eventName
        + " | Date: "
        + eventDate
        + " | Time: "
        + eventTime
        + " | Location: "
        + eventLocation
        + " | Registered: "
        + registeredStudents.size()
        + " | Waitlist: "
        + waitlistedStudents.size();
  }
}
