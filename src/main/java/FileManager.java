import java.util.*;
import java.io.*;

public class FileManager {

  public static void SaveEvents(ArrayList<Event> events) {

    StringBuilder registeredStudents = new StringBuilder();
    StringBuilder waitlistedStudentsString = new StringBuilder();

    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Events.txt"))) {

      for (Event event : events) {

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

          eventstring = eventstring + registeredStudents + "|" + waitlistedStudentsString;
        }

        bw.write(eventstring);
        bw.newLine();
      }
      bw.close();
    } catch (IOException e) {
      System.out.println("Events could not be saved.");
    }
  }

  public static void saveStudents(Student student) {

    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Student.txt"))) {
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
      bw.close();
    } catch (IOException e) {
      System.out.println("Students could not be saved.");
    }
  }
}
