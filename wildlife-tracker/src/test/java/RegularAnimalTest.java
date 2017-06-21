//imports
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.Timestamp;

//class RegularAnimalTest
public class RegularAnimalTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  // Is true if the animal is instantiated correctly
  @Test
  public void animal_instantiatesCorrectly_true() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    assertTrue(testAnimal instanceof RegularAnimal);
  }

//new regular animals should inatantiate without an id
  @Test
  public void animal_instantiatesWithoutId_0() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    assertEquals(0, testAnimal.getId());
  }

//throws an exception if the ranger tries to instantiate a regular animal with an empty name
  @Test(expected = IllegalArgumentException.class)
  public void animal_cannotInstantiateEmptyName_IllegalArgumentException() {
    RegularAnimal testAnimal = new RegularAnimal("");
  }

  // regular animal should be instantiated with the name
  @Test
  public void animal_instantiatesWithName_Rabbit() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    assertEquals("Rabbit", testAnimal.getName());
  }

// set a new regula animal name
  @Test
  public void setName_setsANewName_Goat() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    testAnimal.setName("Goat");
    assertEquals("Goat", testAnimal.getName());
  }

//throws an exception if the ranger tries to set an empty name of the regular animal
  @Test(expected = IllegalArgumentException.class)
  public void setName_cannotSetEmptyName_IllegalArgumentException() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    testAnimal.setName("");
  }

//saves the name of the regular animal
  @Test
  public void save_savesNameToDB_Rabbit() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    testAnimal.save();
    RegularAnimal savedRegularAnimal = RegularAnimal.find(testAnimal.getId());
    assertEquals("Rabbit", savedRegularAnimal.getName());
  }

//updates the name of the regular animal
  @Test
  public void update_preservesOriginalName_Rabbit() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    testAnimal.save();
    testAnimal.update();
    RegularAnimal savedRegularAnimal = RegularAnimal.find(testAnimal.getId());
    assertEquals("Rabbit", savedRegularAnimal.getName());
  }

//updates the new regular animal name
  @Test
  public void update_savesNewNameToDB_Goat() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    testAnimal.save();
    testAnimal.setName("Goat");
    testAnimal.update();
    RegularAnimal savedRegularAnimal = RegularAnimal.find(testAnimal.getId());
    assertEquals("Goat", savedRegularAnimal.getName());
  }

  // saves the id of the regular animal in the database
  @Test
  public void save_setsTheId_int() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    testAnimal.save();
    assertTrue(testAnimal.getId() > 0);
  }

// save the regular animal in the database
  @Test
  public void save_insertsObjectIntoDB_true() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    testAnimal.save();
    RegularAnimal savedRegularAnimal = RegularAnimal.find(testAnimal.getId());
    assertTrue(testAnimal.equals(savedRegularAnimal));
  }

//throw an exception if the ranger tries to save the existing name into the database
  @Test(expected = IllegalArgumentException.class)
  public void save_cannotSaveIfNameAlreadyExists_IllegalArgumentException() {
    RegularAnimal firstRegularAnimal = new RegularAnimal("Rabbit");
    firstRegularAnimal.save();
    RegularAnimal secondRegularAnimal = new RegularAnimal("Rabbit");
    secondRegularAnimal.save();
  }

//delete the regular animal in the database
  @Test
  public void delete_removesObjectFromDB_null() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    testAnimal.save();
    testAnimal.delete();
    RegularAnimal savedRegularAnimal = RegularAnimal.find(testAnimal.getId());
    assertEquals(null, savedRegularAnimal);
  }

//update the regular animal in the database
  @Test
  public void update_preservesOriginalId_true() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    testAnimal.save();
    testAnimal.update();
    RegularAnimal savedRegularAnimal = RegularAnimal.find(testAnimal.getId());
    assertEquals(testAnimal.getId(), savedRegularAnimal.getId());
  }

//to prevent the ranger from updating a regular animals name to look similar to the existing regular animal in the database
  @Test(expected = IllegalArgumentException.class)
  public void update_cannotSaveIfNameAlreadyExists_IllegalArgumentException() {
    RegularAnimal firstRegularAnimal = new RegularAnimal("Rabbit");
    firstRegularAnimal.save();
    RegularAnimal secondRegularAnimal = new RegularAnimal("Goat");
    secondRegularAnimal.save();
    secondRegularAnimal.setName("Rabbit");
    secondRegularAnimal.update();
  }

//return true if the ranger gets regular animals within the database
  @Test
  public void all_getsAllObjectsFromDatabase_true() {
    RegularAnimal firstRegularAnimal = new RegularAnimal("Rabbit");
    firstRegularAnimal.save();
    RegularAnimal secondRegularAnimal = new RegularAnimal("Goat");
    secondRegularAnimal.save();
    RegularAnimal[] expected = { firstRegularAnimal, secondRegularAnimal };
    assertTrue(RegularAnimal.all().containsAll(Arrays.asList(expected)));
  }

//return nothing if the searched regular animal don't exist in the database
  @Test
  public void search_returnsNothingForUnknownValue_emptyList() {
    RegularAnimal firstRegularAnimal = new RegularAnimal("Rabbit");
    firstRegularAnimal.save();
    RegularAnimal secondRegularAnimal = new RegularAnimal("Goat");
    secondRegularAnimal.save();
    List<RegularAnimal> foundRegularAnimals = RegularAnimal.search("fox");
    assertEquals(Collections.<RegularAnimal>emptyList(), foundRegularAnimals);
  }

// return true if the data of the regular animals searched are true
  @Test
  public void search_returnsAllMatchingObjects_true() {
    RegularAnimal firstRegularAnimal = new RegularAnimal("Bobcat");
    firstRegularAnimal.save();
    RegularAnimal secondRegularAnimal = new RegularAnimal("House Cat");
    secondRegularAnimal.save();
    RegularAnimal thirdRegularAnimal = new RegularAnimal("Rabbit");
    thirdRegularAnimal.save();
    List<RegularAnimal> foundRegularAnimals = RegularAnimal.search("cat");
    RegularAnimal[] expected = { firstRegularAnimal, secondRegularAnimal };
    assertEquals(Arrays.asList(expected), foundRegularAnimals);
  }

  //return true if the regular animal details are true
  @Test
  public void equals_objectIsEqualIfAllPropertiesAreEqual_true() {
    RegularAnimal firstRegularAnimal = new RegularAnimal("Rabbit");
    RegularAnimal secondRegularAnimal = new RegularAnimal("Rabbit");
    assertTrue(firstRegularAnimal.equals(secondRegularAnimal));
  }

//sightings associated with the id
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
    List<Sighting> foundSighting = testAnimal.getSightings();
    Sighting[] expected = { testSighting };
    assertTrue(foundSighting.containsAll(Arrays.asList(expected)));
  }

}
