import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
public class Event {

    private int eventId;
    private String eventName;
    private LocalTime eventTime;
    private String eventLocation;
    private LocalDate eventDate;
    private int maxParticipants;
    private List<Student> waitlistedStudents;
    private List<Student> registeredStudents;

    public Event(int eventId, String eventName, LocalDate eventDate, LocalTime eventTime, String eventLocation, int maxParticipants) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
        this.maxParticipants = maxParticipants;

    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
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

    public void  getWaitlistedStudents() {

        for  (Student student : waitlistedStudents) {
            System.out.println(student);
        }



    }

    public void  getRegisteredStudents() {
        for  (Student student : registeredStudents) {
            System.out.println(student);
        }
    }





}
