import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        EventStore eventStorage = new EventStore();

        System.out.println("Welcome to the Richfield Events ");
        System.out.println("Please enter your role");
        System.out.println("1.Student");
        System.out.println("2.Staff");
        Scanner sc = new Scanner(System.in);
        int role = Integer.parseInt(sc.nextLine());

        if (role == 1) {
            String userType= "Student";

            System.out.println("Role: "+userType);
            System.out.println("Please enter your name: ");
            String name = sc.nextLine();
            System.out.println("Please enter your email: ");
            String email = sc.nextLine();
            System.out.println("Enter your course");
            String course = sc.nextLine();
            System.out.println("Please enter your Student number: ");
            int studentNumber = Integer.parseInt(sc.nextLine());

            Student student1= new Student(name,studentNumber,course,email);

            System.out.println("What would you like to do?");
            System.out.println("1. View Events");
            System.out.println("2.View registered events");
            String action = sc.nextLine();

            switch (action) {
                case "1":
                    student1.getEvents(eventStorage);
                    break;

            }


        } else if (role == 2){

            String userType="Staff";
            System.out.println("Role: "+userType);
            System.out.println("Please enter your name: ");
            String name = sc.nextLine();
            System.out.println("Please enter your email: ");
            String email = sc.nextLine();

            Staff staff1 = new Staff(name,email,userType);
            System.out.println("What would you like to do?");
            System.out.println("1. View Events");
            System.out.println("2.View registered events");
            String action = sc.nextLine();
            switch (action) {
                case "1":

            }


    }

    }


}

