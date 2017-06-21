// imports
import java.util.List;
import org.sql2o.*;

//abstract class Animal implements DatabaseManagement
public abstract class Animal implements DatabaseManagement {
  protected int id;
  protected String name;
  protected String type;
  private static final int MIN_NAME_LENGTH = 1;

//getter and setter methods
  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    if(DatabaseManagement.nameValidation(name)) {
      this.name = name;
    }
  }

  public String getType() {
    return this.type;
  }

//Arraylist get sightings of endangered animals
  public List<Sighting> getSightings() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE animal_id = :animal_id;";
      return con.createQuery(sql)
        .addParameter("animal_id", this.id)
        .addColumnMapping("time_of_sighting", "timeOfSighting")
        .addColumnMapping("animal_id", "animalId")
        .addColumnMapping("location_id", "locationId")
        .addColumnMapping("ranger_id", "rangerId")
        .executeAndFetch(Sighting.class);
    }
  }

//to check if the names of animals exists, if not then it adds
  public static boolean nameExists(String name, int id) {
    Integer count = 0;
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT count(name) FROM animals WHERE name = :name AND id != :id;";
      count = con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeScalar(Integer.class);
    }
    return count != 0;
  }

//checks if the ids of the animals exists, if not then still it adds
  public static boolean idExists(int id) {
    Integer count = 0;
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT count(id) FROM animals WHERE id = :id;";
      count = con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("id", id)
        .executeScalar(Integer.class);
    }
    return count != 0;
  }

//get Animals TYpe
  public static String getAnimalType(int id) {
    String type;
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT type FROM animals WHERE id = :id;";
      type = con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("id", id)
        .executeScalar(String.class);
    }
    return type;
  }

//get Animals name
  public static String getAnimalName(int id) {
    String name;
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT name FROM animals WHERE id = :id;";
      name = con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("id", id)
        .executeScalar(String.class);
    }
    return name;
  }

//to delete the animal if its dead
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM animals WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

}
