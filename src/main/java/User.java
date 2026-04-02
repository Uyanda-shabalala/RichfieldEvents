abstract class User {

    private String name;
    private String email="@my.richfield.ac.za";

    public User(String name, String email) {
        this.name = name;
        this.email = email ;
    }

    abstract String getName();
    abstract String getEmail();
    abstract String getRole();


}
