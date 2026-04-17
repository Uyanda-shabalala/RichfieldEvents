import java.util.*;

public class Student extends User {

  final String role = "Student";
  List<Event> registeredEvents =
      new ArrayList<>(); // stores events the student object is registered for
  private int studentNumber;
  private String course;

  public Student(String name, int studentNumber, String course, String email) {
    super(name, email);

    this.course = course;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getStudentNumber() {
    return studentNumber;
  }

  public void setStudentNumber(int studentNumber) {
    this.studentNumber = studentNumber;
  }

  @Override
  public String getRole() {
    return role;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void getRegisteredEvents() {
    for (Event event : registeredEvents) {
      System.out.println(event);
    }
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  // Logic

  public void registerForEvent(EventStore tempEventStorage, int eventId) {
    Event event =
        tempEventStorage.findEventById(
            eventId); // event to be registered for will be stored in the event variable

    if (event == null) {
      System.out.println("Event not found. Please check the ID and try again.");
      return;
    }

    boolean successfulRegistration = event.registerStudent(this);
    if (successfulRegistration) {
      registeredEvents.add(event);
      System.out.println(getName() + " registered for " + event.getEventName());
    }
  }

  public void deregisterForEvent(EventStore tempEventStorage, int eventId) {
    Event event = tempEventStorage.findEventById(eventId);

    if (event == null) {
      System.out.println("Event not found. Please check the ID and try again.");
      return;
    }

    boolean studentsDeregistered = event.deregisterStudentFromEvent(this);
    if (studentsDeregistered) {
      registeredEvents.remove(event);
      System.out.println(this.name + " deregistered for " + event.getEventName());
    }
  }

  public String viewRegistrationStatus(EventStore tempEventStorage, int eventId) {

    Event event = tempEventStorage.findEventById(eventId);
    if (event == null) {
      return "Event not found. Please check the ID and try again.";
    }
    Queue<Student> waitlist = event.getWaitlistedStudents();
    List<Student> registeredStudents = event.getRegisteredStudents();

    if (waitlist.contains(this)) {
      int count = 0;
      for (Student s : waitlist) {
        if (s.equals(this)) {
          break;
        }
        count++;
      }
      return "You're currently number " + (count + 1) + " on the waitlist";
    } else if (registeredStudents.contains(this)) {

      return "You are currently registered for the " + event.getEventName() + " event";
    }

    return "error in fetching data";
  }
}
