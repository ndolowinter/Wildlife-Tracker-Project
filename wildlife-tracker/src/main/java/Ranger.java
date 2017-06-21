//imports
import java.util.List;
import org.sql2o.*;

//class Ranger implementing interface DatabaseManagement
public class Ranger implements DatabaseManagement {
  private int id;
  private String userName;
  private String firstName;
  private String lastName;
  private int badge;
  private long phone;

//constructor ranger
  public Ranger(String userName, String firstName, String lastName, int badge, long phone) {
    if(DatabaseManagement.nameValidation(userName)) {
      this.userName = userName;
    }
    if(DatabaseManagement.nameValidation(firstName)) {
      this.firstName = firstName;
    }
    if(DatabaseManagement.nameValidation(lastName)) {
      this.lastName = lastName;
    }
    this.badge = badge;
    this.phone = phone;
  }

//getters and setters methods
  public int getId() {
    return this.id;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    if(DatabaseManagement.nameValidation(userName)) {
      this.userName = userName;
    }
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    if(DatabaseManagement.nameValidation(firstName)) {
      this.firstName = firstName;
    }
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    if(DatabaseManagement.nameValidation(lastName)) {
      this.lastName = lastName;
    }
  }

  public int getBadge() {
    return this.badge;
  }

  public void setBadge(int badge) {
    this.badge = badge;
  }

  public long getPhone() {
    return this.phone;
  }

  public void setPhone(long phone) {
    this.phone = phone;
  }

//save rangers to the database
  public void save() {
    if (Ranger.userNameExists(this.userName, this.id)) {
      throw new IllegalArgumentException("Error: Name already exists.");
    } else {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO rangers (username, firstname, lastname, badge, phone) VALUES (:username, :firstname, :lastname, :badge, :phone);";
        this.id = (int) con.createQuery(sql, true)
        .addParameter("username", this.userName)
        .addParameter("firstname", this.firstName)
        .addParameter("lastname", this.lastName)
        .addParameter("badge", this.badge)
        .addParameter("phone", this.phone)
        .executeUpdate()
        .getKey();
      }
    }
  }

//update the rangers identity
  public void update() {
    if (Ranger.userNameExists(this.userName, this.id)) {
      throw new IllegalArgumentException("Error: Name already exists.");
    } else {
      try(Connection con = DB.sql2o.open()) {
        String sql = "UPDATE rangers SET username = :username, firstname = :firstname, lastname = :lastname, badge = :badge, phone = :phone WHERE id = :id;";
        con.createQuery(sql)
          .addParameter("id", this.id)
          .addParameter("username", this.userName)
          .addParameter("firstname", this.firstName)
          .addParameter("lastname", this.lastName)
          .addParameter("badge", this.badge)
          .addParameter("phone", this.phone)
          .executeUpdate();
      }
    }
  }

//delete the ranger if incase is fired, dead, quitted his/her job or retired
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM rangers WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

//lists the sightings which the rangers have done
  public List<Sighting> getSightings() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE ranger_id = :ranger_id;";
      return con.createQuery(sql)
        .addParameter("ranger_id", this.id)
        .addColumnMapping("time_of_sighting", "timeOfSighting")
        .addColumnMapping("animal_id", "animalId")
        .addColumnMapping("location_id", "locationId")
        .addColumnMapping("ranger_id", "rangerId")
        .executeAndFetch(Sighting.class);
    }
  }

//check if the ranger doing the sightings if they exists in the database
  public static boolean userNameExists(String userName, int id) {
    Integer count = 0;
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT count(username) FROM rangers WHERE username = :username AND id != :id;";
      count = con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("username", userName)
        .addParameter("id", id)
        .executeScalar(Integer.class);
    }
    return count != 0;
  }

//check if their ids too exists in the database
  public static boolean idExists(int id) {
    Integer count = 0;
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT count(username) FROM rangers WHERE id = :id;";
      count = con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("id", id)
        .executeScalar(Integer.class);
    }
    return count != 0;
  }

//used to get rangers usernames
  public static String getRangerUserName(int id) {
    String name;
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT username FROM rangers WHERE id = :id;";
      name = con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("id", id)
        .executeScalar(String.class);
    }
    return name;
  }

//lists all the rangers working in the forest department
  public static List<Ranger> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM rangers;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .executeAndFetch(Ranger.class);
    }
  }

//used to find the ranger according to their ids
  public static Ranger find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM rangers WHERE id = :id;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("id", id)
        .executeAndFetchFirst(Ranger.class);
    }
  }

//search the rangers
  public static List<Ranger> search(String search) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM rangers WHERE username ~* :search OR firstname ~* :search OR lastname ~* :search;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("search", ".*" + search + ".*")
        .executeAndFetch(Ranger.class);
    }
  }

//checks if ranger conducting sightings is genuine, if he/she is, it provides their credentials
  @Override
  public boolean equals(Object otherObject) {
    if (!(otherObject instanceof Ranger)) {
      return false;
    } else {
      Ranger otherRanger = (Ranger) otherObject;
      return this.getId() == otherRanger.getId() &&
        this.getFirstName().equals(otherRanger.getFirstName()) &&
        this.getLastName().equals(otherRanger.getLastName()) &&
        this.getBadge() == otherRanger.getBadge() &&
        this.getPhone() == otherRanger.getPhone();
    }
  }

}
