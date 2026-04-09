
import java.util.*;

public class Student extends User {

    private int studentNumber;
    private String course;
     final  String role="Student";
     List <Event> registeredEvents= new ArrayList<>(); //stores events the student object is registered for

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

    @Override
    public String getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public List <Event> getRegisteredEvents() {
        return registeredEvents;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public String getCourse() {
        return course;
    }


    //Logic

    public void registerForEvent(EventStore tempEventStorage,int eventId) {
        Event event= tempEventStorage.findEventById(eventId); //event to be registered for will be stored in the event variable


        boolean successfulRegistration = event.registerStudent(this);
        if (successfulRegistration) {
            registeredEvents.add(event);
            System.out.println(getName() + " registered for " + event.getEventName());
        }

    }

    public void getEvents(EventStore tempEventStorage) {
        for (Event e : tempEventStorage.getAllEvents()) {
            System.out.println(e.getEventId() + ". "+e.getEventName());
        }
    }

    public void deregisterForEvent(EventStore tempEventStorage,int eventId) {
        Event event= tempEventStorage.findEventById(eventId);
        boolean studentDeregisterd=event.deregisterStudentFromEvent(this);
        if (studentDeregisterd) {
            registeredEvents.remove(event);
            System.out.println(this.name + " deregistered for " + event.getEventName());
        }

    }

    public String viewRegistrationStatus(EventStore tempEventStorage,int eventId) {

        Event event= tempEventStorage.findEventById(eventId);
        List<Student> waitlist=event.getWaitlistedStudents();
        List <Student> registeredStudents=event.getRegisteredStudents();
        if (waitlist.contains(this)) {
             return "You're currently number "+(waitlist.indexOf(this))+1 +"on the waitlist"+event.getWaitlistedStudents().size();

        } else if (registeredStudents.contains(this)) {

            return "You are currently registered for the "+event.getEventName()+" event";
        }


        return "error in fetching data";
    }




}