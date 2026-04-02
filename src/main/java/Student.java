import java.util.*;

public class Student extends User {
    private String name;
    private String email;
    private int studentNumber;
    private String Course;
     final  String role="Student";

    public Student(String name, int studentNumber, String course, String email) {
        super(name, email);

        this.Course = course;

    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getEmail() {
        return email;
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

    public void setCourse(String Course) {
        this.Course = Course;
    }


}