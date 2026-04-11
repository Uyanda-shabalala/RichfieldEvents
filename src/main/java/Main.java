import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  static void main() {

    try {
      EventStore eventStorage = new EventStore();

      System.out.println("Welcome to the Richfield Events ");
      System.out.println("Please enter your role");
      System.out.println("1.Student");
      System.out.println("2.Staff");
      Scanner sc = new Scanner(System.in);
      int role = Integer.parseInt(sc.nextLine());

      if (role == 1) {
        String userType = "Student";

        System.out.println("Role: " + userType);
        System.out.println("Please enter your name: ");
        String name = sc.nextLine();
        System.out.println("Please enter your email: ");
        String email = sc.nextLine();
        System.out.println("Enter your course");
        String course = sc.nextLine();
        System.out.println("Please enter your Student number: ");
        int studentNumber = Integer.parseInt(sc.nextLine());

        Student student1 = new Student(name, studentNumber, course, email);
        boolean endprogram = false;

        while (!endprogram) {

          System.out.println("What would you like to do?");
          System.out.println("===============================================================");
          System.out.println("1. View Events");
          System.out.println("2. View Events You've registered for");
          System.out.println("3. Register for event ");
          System.out.println("4. Deregister from event");
          System.out.println("5. Exit");
          System.out.println("============================================================");
          String action = sc.nextLine();

          endprogram = false;

          while (!endprogram) {
            switch (action) {
              case "1":
                student1.getEvents(eventStorage);
                break;

              case "2":
                student1.getRegisteredEvents();
                break;

              case "3":
                try {
                  System.out.println("Enter the id of  the event you would like to register for ");
                  int eventId = Integer.parseInt(sc.nextLine());
                  student1.registerForEvent(eventStorage, eventId);
                } catch (NumberFormatException e) {
                  System.out.println("Please enter a valid id");
                }
                break;

              case "4":
                int eventId = Integer.parseInt(sc.nextLine());
                student1.deregisterForEvent(eventStorage, eventId);
                break;

              case "5":
                endprogram = true;
                break;
            }
          }
        }
      } else if (role == 2) {

        String userType = "Staff";
        System.out.println("Role: " + userType);
        System.out.println("Please enter your name: ");
        String name = sc.nextLine();
        System.out.println("Please enter your email: ");
        String email = sc.nextLine();

        Staff staff1 = new Staff(name, email, userType);

        boolean endProgram = false;

        while (!endProgram) {

          String action = sc.nextLine();

          System.out.println("What would you like to do?");
          System.out.println("============================================================");

          System.out.println("1. View Events");
          System.out.println("2. Create an event");
          System.out.println("3. Cancel an events");
          System.out.println("4. Update an event");
          System.out.println("5. View number of student registered for events");
          System.out.println("6. Sort Events by Date");
          System.out.println("============================================================");

          System.out.println("7. End program");

          switch (action) {
            case "1":
              System.out.println(staff1);
              staff1.showEvents(eventStorage);
              break;

            case "2":
              System.out.println("Enter Event Name");
              String eventName = sc.nextLine();
              System.out.println("Enter Event Date in the format YYYY-MM-DD");
              LocalDate eventDate =
                  LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
              System.out.println("Enter Event Time in the format HH:mm:ss ");
              LocalTime eventTime =
                  LocalTime.parse(sc.nextLine(), DateTimeFormatter.ofPattern("HH:mm:ss"));
              System.out.println("Enter Event Location");
              String eventLocation = sc.nextLine();
              System.out.println("Enter the maximum number of participants");
              int maxParticipants = Integer.parseInt(sc.nextLine());
              staff1.createEvent(
                  eventStorage, eventName, eventDate, eventTime, eventLocation, maxParticipants);

              break;

            case "3":
              System.out.println("Enter Event ID");
              int eventID = Integer.parseInt(sc.nextLine());
              staff1.cancelEvent(eventID, eventStorage);

              break;

            case "4":
              try {
                System.out.println("Enter Event ID");
                eventID = Integer.parseInt(sc.nextLine());

                System.out.println("Enter Event Name");
                eventName = sc.nextLine();

                System.out.println("Enter Event Date in the format YYYY-MM-DD");
                eventDate =
                    LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                System.out.println("Enter Event Time in the format HH:mm:ss ");
                eventTime = LocalTime.parse(sc.nextLine(), DateTimeFormatter.ofPattern("HH:mm:ss"));
                System.out.println("Enter Event Location");
                eventLocation = sc.nextLine();
                staff1.updateEvent(
                    eventID, eventName, eventTime, eventDate, eventLocation, eventStorage);

              } catch (DateTimeParseException e) {
                System.out.println(
                    "Invalid Date Format Please enter the date in the format YYYY-MM-DD");
              }

              break;
            case "5":
              staff1.viewParticipants(eventStorage);
              break;

            case "6":
              endProgram = true;
              break;

            default:
              System.out.println("Invalid Action");
          }
        }
      }
    } catch (NumberFormatException e) {
      System.out.println("Please enter a valid number");
    }
  }
}
