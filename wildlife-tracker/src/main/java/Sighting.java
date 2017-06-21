//imports
import java.sql.Timestamp;
import java.util.List;
import org.sql2o.*;

//class sighting
public class Sighting {
  private int id;
  private int animalId;
  private int locationId;
  private int rangerId;
  private Timestamp timeOfSighting;

//constructor sighting
  public Sighting (int animalId, int locationId, int rangerId, Timestamp timeOfSighting) {
    if(!Animal.idExists(animalId)) {
      throw new IllegalArgumentException("Error: invalid animalId");
    }
    this.animalId = animalId;
    if(!Location.idExists(locationId)) {
      throw new IllegalArgumentException("Error: invalid locationId");
    }
    this.locationId = locationId;
    if(!Ranger.idExists(rangerId)) {
      throw new IllegalArgumentException("Error: invalid locationId");
    }
    this.rangerId = rangerId;
    this.timeOfSighting = timeOfSighting;
  }

//getters and setter methods
  public int getId() {
    return this.id;
  }

  public int getAnimalId() {
    return this.animalId;
  }

  public void setAnimalId(int animalId) {
    if(!Animal.idExists(animalId)) {
      throw new IllegalArgumentException("Error: invalid animalId");
    }
    this.animalId = animalId;
  }

  public int getLocationId() {
    return this.locationId;
  }

  public void setLocationId(int locationId) {
    if(!Location.idExists(locationId)) {
      throw new IllegalArgumentException("Error: invalid locationId");
    }
    this.locationId = locationId;
  }

  public int getRangerId() {
    return this.rangerId;
  }

  public void setRangerId(int rangerId) {
    if(!Ranger.idExists(rangerId)) {
      throw new IllegalArgumentException("Error: invalid rangerId");
    }
    this.rangerId = rangerId;
  }

  public Timestamp getTimeOfSighting() {
    return this.timeOfSighting;
  }

  public void setTimeOfSighting(Timestamp timeOfSighting) {
    this.timeOfSighting = timeOfSighting;
  }

//save sightings
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (time_of_sighting, animal_id, location_id, ranger_id) VALUES (:time_of_sighting, :animal_id, :location_id, :ranger_id);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("time_of_sighting", this.timeOfSighting)
      .addParameter("animal_id", this.animalId)
      .addParameter("location_id", this.locationId)
      .addParameter("ranger_id", this.rangerId)
      .executeUpdate()
      .getKey();
    }
  }

//update sightings
  public void update() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE sightings SET time_of_sighting = :time_of_sighting, animal_id = :animal_id, location_id = :location_id, ranger_id = :ranger_id WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .addParameter("time_of_sighting", this.timeOfSighting)
        .addParameter("animal_id", this.animalId)
        .addParameter("location_id", this.locationId)
        .addParameter("ranger_id", this.rangerId)
        .executeUpdate();
    }
  }

//delete sighting
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM sightings WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

//find sighting by id
  public static Sighting find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE id = :id;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("id", id)
        .addColumnMapping("time_of_sighting", "timeOfSighting")
        .addColumnMapping("animal_id", "animalId")
        .addColumnMapping("location_id", "locationId")
        .addColumnMapping("ranger_id", "rangerId")
        .executeAndFetchFirst(Sighting.class);
    }
  }

//display lists of sightings
  public static List<Sighting> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings;";
      return con.createQuery(sql)
        .addColumnMapping("time_of_sighting", "timeOfSighting")
        .addColumnMapping("animal_id", "animalId")
        .addColumnMapping("location_id", "locationId")
        .addColumnMapping("ranger_id", "rangerId")
        .executeAndFetch(Sighting.class);
    }
  }

//checks if Sighting details are true
  @Override
  public boolean equals(Object otherObject) {
    if (!(otherObject instanceof Sighting)) {
      return false;
    } else {
      Sighting otherSighting = (Sighting) otherObject;
      return this.getId() == otherSighting.getId() &&
        this.getAnimalId() == otherSighting.getAnimalId() &&
        this.getLocationId() == otherSighting.getLocationId() &&
        this.getRangerId() == otherSighting.getRangerId();
    }
  }

}
