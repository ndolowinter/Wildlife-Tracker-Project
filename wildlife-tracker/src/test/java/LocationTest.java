//imports
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.sql.Timestamp;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

//class location test
public class LocationTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  // ranger is instantiated correctly
  @Test
  public void ranger_instantiatesCorrectly_true() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    assertTrue(testLocation instanceof Location);
  }

//new ranger is instantiated without id
  @Test
  public void ranger_instantiatesWithoutId_0() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    assertEquals(0, testLocation.getId());
  }

  // userName is inatantiated correctly
  @Test
  public void ranger_instantiatesWithName_User() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    assertEquals("Near bridge", testLocation.getName());
  }

//to prevent instantiating an empty userName
  @Test(expected = IllegalArgumentException.class)
  public void ranger_cannotInstantiateEmptyUserName_IllegalArgumentException() {
    Location testLocation = new Location("", 1.525, -2.311);
  }

//set new userName
  @Test
  public void setUserName_setsANewName_NewName() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.setName("NewName");
    assertEquals("NewName", testLocation.getName());
  }

//prevent saving an empty location name
  @Test(expected = IllegalArgumentException.class)
  public void setUserName_cannotSetEmptyName_IllegalArgumentException() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.setName("");
  }

//save the location name
  @Test
  public void save_savesNameToDB_Nearbridge() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.save();
    Location savedLocation = Location.find(testLocation.getId());
    assertEquals("Near bridge", savedLocation.getName());
  }

//update the location name
  @Test
  public void update_preservesOriginalName_Nearbridge() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.save();
    testLocation.update();
    Location savedLocation = Location.find(testLocation.getId());
    assertEquals("Near bridge", savedLocation.getName());
  }

//update the new location name
  @Test
  public void update_savesNewNameToDB_NewLocation() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.save();
    testLocation.setName("New Location");
    testLocation.update();
    Location savedLocation = Location.find(testLocation.getId());
    assertEquals("New Location", savedLocation.getName());
  }


  // x coordinate is instantiated correctly by the rangers
  @Test
  public void ranger_instantiatesWithXCoord_1_525() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    assertEquals(1.525, testLocation.getXCoord(), 0);
  }

//set new x coordinate of the location
  @Test
  public void setXCoord_setsANewXCoord_3_885() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.setXCoord(3.885);
    assertEquals(3.885, testLocation.getXCoord(), 0);
  }

//saves the x coordinates
  @Test
  public void save_savesXCoordToDB_1_525() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.save();
    Location savedLocation = Location.find(testLocation.getId());
    assertEquals(1.525, savedLocation.getXCoord(), 0);
  }

//update the x coordinate of the location
  @Test
  public void update_preservesOriginalXCoord_1_525() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.save();
    testLocation.update();
    Location savedLocation = Location.find(testLocation.getId());
    assertEquals(1.525, savedLocation.getXCoord(), 0);
  }

//update new x coordinate in the database
  @Test
  public void update_savesNewXCoordToDB_3_885() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.save();
    testLocation.setXCoord(3.885);
    testLocation.update();
    Location savedLocation = Location.find(testLocation.getId());
    assertEquals(3.885, savedLocation.getXCoord(), 0);
  }

  // ranger should instantiate correctly with the y coordinates
  @Test
  public void ranger_instantiatesWithYCoord_2_311() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    assertEquals(-2.311, testLocation.getYCoord(), 0);
  }

//saves the new y coordinate of the location to the database
  @Test
  public void setYCoord_setsANewYCoord_4_243() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.setYCoord(4.243);
    assertEquals(4.243, testLocation.getYCoord(), 0);
  }

//saves the y coordinates of the location to the database
  @Test
  public void save_savesYCoordToDB_2_311() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.save();
    Location savedLocation = Location.find(testLocation.getId());
    assertEquals(-2.311, savedLocation.getYCoord(), 0);
  }

//updates coordinates of the location into the database
  @Test
  public void update_preservesOriginalYCoord_2_311() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.save();
    testLocation.update();
    Location savedLocation = Location.find(testLocation.getId());
    assertEquals(-2.311, savedLocation.getYCoord(), 0);
  }

//updates the coordinates of the new location into the database
  @Test
  public void update_savesNewXCoordToDB_4_243() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.save();
    testLocation.setYCoord(4.243);
    testLocation.update();
    Location savedLocation = Location.find(testLocation.getId());
    assertEquals(4.243, savedLocation.getYCoord(), 0);
  }

  // saves the id of the location into the database
  @Test
  public void save_setsTheId_int() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.save();
    assertTrue(testLocation.getId() > 0);
  }

//save locations into the database
  @Test
  public void save_insertsObjectIntoDB_true() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.save();
    Location savedLocation = Location.find(testLocation.getId());
    assertTrue(testLocation.getName().equals(savedLocation.getName()));
  }

//prevents the ranger from saving location already saved in the database to minimise redundancy
  @Test(expected = IllegalArgumentException.class)
  public void save_cannotSaveIfNameAlreadyExists_IllegalArgumentException() {
    Location firstLocation = new Location("Near bridge", 1.525, -2.311);
    firstLocation.save();
    Location secondLocation = new Location("Near bridge", 1.525, -2.311);
    secondLocation.save();
  }

//update the locations
  @Test
  public void update_preservesOriginalId_true() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.save();
    testLocation.update();
    Location savedLocation = Location.find(testLocation.getId());
    assertEquals(testLocation.getId(), savedLocation.getId());
  }

//throw an exception if the location name exists
  @Test(expected = IllegalArgumentException.class)
  public void update_cannotSaveIfNameAlreadyExists_IllegalArgumentException() {
    Location firstLocation = new Location("Near bridge", 1.525, -2.311);
    firstLocation.save();
    Location secondLocation = new Location("New location", 1.525, -2.311);
    secondLocation.save();
    secondLocation.setName("Near bridge");
    secondLocation.update();
  }

//get all the locations added within the database
  @Test
  public void all_getsAllObjectsFromDatabase_true() {
    Location firstLocation = new Location("Near bridge", 1.525, -2.311);
    firstLocation.save();
    Location secondLocation = new Location("New Location", 1.525, -2.311);
    secondLocation.save();
    Location[] expected = { firstLocation, secondLocation };
    assertTrue(Location.all().containsAll(Arrays.asList(expected)));
  }

//delete location if the sightings of endangered animals are no longer there
  @Test
  public void delete_removesObjectFromDB_null() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.save();
    testLocation.delete();
    Location savedLocation = Location.find(testLocation.getId());
    assertEquals(null, savedLocation);
  }

//return nothing for unknown Location
  @Test
  public void search_returnsNothingForUnknownValue_emptyList() {
    Location firstLocation = new Location("Near bridge", 1.525, -2.311);
    firstLocation.save();
    Location secondLocation = new Location("New Location", 1.525, -2.311);
    secondLocation.save();
    List<Location> foundLocations = Location.search("blank");
    assertEquals(Collections.<Location>emptyList(), foundLocations);
  }

//return true if the locations searched are correct
  @Test
  public void search_returnsAllMatchingObjects_true() {
    Location firstLocation = new Location("First Location", 1.525, -2.311);
    firstLocation.save();
    Location secondLocation = new Location("Second Location", 1.525, -2.311);
    secondLocation.save();
    Location thirdLocation = new Location("Another Place", 1.525, -2.311);
    thirdLocation.save();
    Location[] expected = { firstLocation, secondLocation };
    List<Location> foundLocations = Location.search("loc");
    assertEquals(Arrays.asList(expected), foundLocations);
    assertFalse(foundLocations.contains(thirdLocation));
  }

  //return true if all location properties are equal
  @Test
  public void equals_objectIsEqualIfAllPropertiesAreEqual_true() {
    Location firstLocation = new Location("Near bridge", 1.525, -2.311);
    Location secondLocation = new Location("Near bridge", 1.525, -2.311);
    assertTrue(firstLocation.equals(secondLocation));
  }

//sightings should be associated with the id
  @Test
  public void getSightings_getsSightingAssociatedWithId_Sighting() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    testAnimal.save();
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.save();
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId(), new Timestamp(1L));
    testSighting.save();
    List<Sighting> foundSighting = testLocation.getSightings();
    Sighting[] expected = { testSighting };
    assertTrue(foundSighting.containsAll(Arrays.asList(expected)));
  }

}
