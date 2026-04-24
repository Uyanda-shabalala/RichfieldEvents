abstract class User {
  protected String name;
  protected String email;

  public User(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public User() {}

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public abstract String getRole(); // only this varies per subclass
}
