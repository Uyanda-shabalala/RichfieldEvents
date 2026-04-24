import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.io.*;

public class FileManager {
  public static void saveAll(EventStore eventStore) {
    saveEvents((eventStore.getAllEvents()));
    saveStudents(eventStore.getAllStudents());
    saveStaff(eventStore.getAllStaff());
  }

  public static void saveEvents(ArrayList<Event> events) {

    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Events.txt"))) {

      for (Event event : events) {
        StringBuilder registeredStudents = new StringBuilder();
        StringBuilder waitlistedStudentsString = new StringBuilder();

        String eventstring =
            event.getEventId()
                + "|"
                + event.getEventName()
                + "|"
                + event.getEventTime()
                + "|"
                + event.getEventDate()
                + "|"
                + event.getEventLocation()
                + "|"
                + event.getMaxParticipants();

        for (Student s : event.getRegisteredStudents()) {
          registeredStudents.append(s.getStudentNumber()).append(",");
        }

        for (Student s : event.getWaitlistedStudents()) {
          waitlistedStudentsString.append(s.getStudentNumber()).append(",");
        }
        eventstring = eventstring + "|" + registeredStudents + "|" + waitlistedStudentsString;
        bw.write(eventstring);
        bw.newLine();
      }

    } catch (IOException e) {
      System.out.println("Events could not be saved.");
    }
  }

  public static void saveStudents(List<Student> students) {

    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Students.txt"))) {
      for (Student student : students) {
        String studentString =
            student.getName()
                + "|"
                + student.getStudentNumber()
                + "|"
                + student.getCourse()
                + "|"
                + student.getEmail();
        bw.write(studentString);
        bw.newLine();
      }

    } catch (IOException e) {
      System.out.println("Students could not be saved.");
    }
  }

  public static void saveStaff(List<Staff> staff) {

    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Staff.txt"))) {

      for (Staff staffMember : staff) {

        String staffString = staffMember.getName() + "|" + staffMember.getEmail();
        bw.write(staffString);
        bw.newLine();
      }

    } catch (IOException e) {
      System.out.println("Staff members could not be saved.");
    }
  }

  public static void loadStaff(EventStore eventStore) {

    try (BufferedReader br = new BufferedReader(new FileReader("Staff.txt"))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split("\\|");
        String name = parts[0];
        String email = parts[1];

        Staff staff = new Staff(eventStore, name, email, "Staff");
        eventStore.addStaff(staff);
      }
    } catch (FileNotFoundException e) {
      System.out.println("No staff data found, starting fresh.");
    } catch (IOException e) {
      System.out.println("Error loading staff.");
    }
  }

  public static void loadEvents(EventStore eventStore, List<Student> allStudents) {

    // build the Students Hashmap from the already loaded students list
    Map<Integer, Student> studentMap = new HashMap<>();
    for (Student s : allStudents) {
      studentMap.put(s.getStudentNumber(), s);
    }

    try (BufferedReader br = new BufferedReader(new FileReader("Events.txt"))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split("\\|");

        int eventId = Integer.parseInt(parts[0]);
        String eventName = parts[1];
        LocalTime eventTime = LocalTime.parse(parts[2]);
        LocalDate eventDate = LocalDate.parse(parts[3]);
        String eventLocation = parts[4];
        int maxParticipants = Integer.parseInt(parts[5]);

        Event event =
            new Event(eventId, eventName, eventDate, eventTime, eventLocation, maxParticipants);
        eventStore.addEvent(event);

        // reconnect registered students  array from the 6 position in the event string
        if (parts.length > 6 && !parts[6].isEmpty()) {

          for (String numStr : parts[6].split(",")) {
            if (!numStr.isEmpty()) {
              Student s = studentMap.get(Integer.parseInt(numStr));

              if (s != null) {
                event.getRegisteredStudents().add(s);
                s.getRegisteredEvents().add(event);
              }
            }
          }
        }

        // reconnecting  waitlisted students array from the 7 position in the event string
        if (parts.length > 7 && !parts[7].isEmpty()) {
          for (String studentNumber : parts[7].split(",")) {
            if (!studentNumber.isEmpty()) {
              Student s = studentMap.get(Integer.parseInt(studentNumber));
              if (s != null) {
                event.getWaitlistedStudents().add(s);
              }
            }
          }
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("No event data found, starting fresh.");
    } catch (IOException e) {
      System.out.println("Error loading events.");
    }
  }

  public static void loadStudents(EventStore eventStore) {

    try (BufferedReader br = new BufferedReader(new FileReader("Students.txt"))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split("\\|");
        String name = parts[0];
        int studentNumber = Integer.parseInt(parts[1]);
        String course = parts[2];
        String email = parts[3];

        Student s = new Student(name, studentNumber, course, email);
        eventStore.addStudent(s);
      }
    } catch (FileNotFoundException e) {
      System.out.println("No student data found, starting fresh.");
    } catch (IOException e) {
      System.out.println("Error loading students.");
    }
  }

  public static void loadAll(EventStore eventStore) {
    loadStudents(eventStore);
    loadStaff(eventStore);
    loadEvents(eventStore, eventStore.getAllStudents());
  }
}
