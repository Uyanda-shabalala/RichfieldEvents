import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Event {

    private  int eventId;
    private String eventName;
    private LocalTime eventTime;
    private String eventLocation;
    private LocalDate eventDate;
    private int maxParticipants;
    private  List<Student> waitlistedStudents= new ArrayList<>();
    private List<Student> registeredStudents= new ArrayList<>();
    private static int counter=0;

    //constructor

    public Event(int eventId, String eventName, LocalDate eventDate, LocalTime eventTime, String eventLocation, int maxParticipants) {
        this.eventId = ++counter;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
        this.maxParticipants = maxParticipants;

    }


//getters and setters
    public int getEventId() {
        return eventId;
    }


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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
        this.eventLocation = eventLocation;
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

    public List<Student>  getWaitlistedStudents() {
            return waitlistedStudents;
    }


    public List<Student>  getRegisteredStudents() {
        return registeredStudents;

    }


    //logic

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
        registeredStudents.remove(student);
        return true;
    }



    }


