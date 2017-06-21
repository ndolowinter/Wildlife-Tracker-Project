//imports
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.sql.Timestamp;

//class EndangeredAnimalTest
public class EndangeredAnimalTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  // Check if the endangered animal is instantiated correctly
  @Test
  public void endangeredAnimal_instantiatesCorrectly_true() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    assertTrue(testAnimal instanceof EndangeredAnimal);
  }

//Check if the EndangeredAnimal has been instantiated without Id to give out correct results
  @Test
  public void endangeredAnimal_instantiatesWithoutId_0() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    assertEquals(0, testAnimal.getId());
  }

  // Check if the endangered animal has been instantiated correctly with name
  @Test
  public void endangeredAnimal_instantiatesWithName_Rhino() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    assertEquals("Rhino", testAnimal.getName());
  }

//To prevent an endangered animal to be instantiated with an empty name
  @Test(expected = IllegalArgumentException.class)
  public void endangeredAnimal_cannotInstantiateEmptyName_IllegalArgumentException() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("", 1.5, "Good");
  }

//To set names of endangered animals
  @Test
  public void setName_setsANewName_Panda() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    testAnimal.setName("Panda");
    assertEquals("Panda", testAnimal.getName());
  }

//To prevent placing an empty set name of an endangered animal
  @Test(expected = IllegalArgumentException.class)
  public void setName_cannotSetEmptyName_IllegalArgumentException() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    testAnimal.setName("");
  }

//save name of the endangered animal to the database
  @Test
  public void save_savesNameToDB_Rhino() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    testAnimal.save();
    EndangeredAnimal savedAnimal = EndangeredAnimal.find(testAnimal.getId());
    assertEquals("Rhino", savedAnimal.getName());
  }

//update the endangered animal in the database but preserves its original name
  @Test
  public void update_preservesOriginalName_Rhino() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    testAnimal.save();
    testAnimal.update();
    EndangeredAnimal savedAnimal = EndangeredAnimal.find(testAnimal.getId());
    assertEquals("Rhino", savedAnimal.getName());
  }

//update the set endangered animal to the database
  @Test
  public void update_savesNewNameToDB_Panda() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    testAnimal.save();
    testAnimal.setName("Panda");
    testAnimal.update();
    EndangeredAnimal savedAnimal = EndangeredAnimal.find(testAnimal.getId());
    assertEquals("Panda", savedAnimal.getName());
  }

  // endangered animal instantiates correctly to its age
  @Test
  public void endangeredAnimal_instantiatesWithAge_1_5() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    assertEquals(1.5, testAnimal.getAge(), 0);
  }

//to prevent an endangered animal to be instantiated in a negative
  @Test(expected = IllegalArgumentException.class)
  public void endangeredAnimal_cannotInstantiateNegativeAge_IllegalArgumentException() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", -1.5, "Good");
  }

//set age of an endangered animal
  @Test
  public void setAge_setsANewAge_2_1() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    testAnimal.setAge(2.1);
    assertEquals(2.1, testAnimal.getAge(), 0);
  }

//to prevent the set endangered animal's age not to be a negative
  @Test(expected = IllegalArgumentException.class)
  public void setAge_cannotSetNegativeAge_IllegalArgumentException() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    testAnimal.setAge(-1.5);
  }

//save age to database
  @Test
  public void save_savesAgeToDB_1_5() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    testAnimal.save();
    EndangeredAnimal savedAnimal = EndangeredAnimal.find(testAnimal.getId());
    assertEquals(1.5, savedAnimal.getAge(), 0);
  }

//updates the age of the endangered animal
  @Test
  public void update_preservesOriginalAge_1_5() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    testAnimal.save();
    testAnimal.update();
    EndangeredAnimal savedAnimal = EndangeredAnimal.find(testAnimal.getId());
    assertEquals(1.5, savedAnimal.getAge(), 0);
  }

//to update the age of the set age(new endangered animal age ie birth) into the database
  @Test
  public void update_savesNewAgeToDB_2_1() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    testAnimal.save();
    testAnimal.setAge(2.1);
    testAnimal.update();
    EndangeredAnimal savedAnimal = EndangeredAnimal.find(testAnimal.getId());
    assertEquals(2.1, savedAnimal.getAge(), 0);
  }

  // instantates if the endangered animal is good
  @Test
  public void endangeredAnimal_instantiatesWithHealth_Good() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    assertEquals("Good", testAnimal.getHealth());
  }

//set the health of endangered animal
  @Test
  public void setHealth_setsANewHealth_Poor() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    testAnimal.setHealth("Poor");
    assertEquals("Poor", testAnimal.getHealth());
  }

//save the health of the endangered animal to the database
  @Test
  public void save_savesHealthToDB_Good() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    testAnimal.save();
    EndangeredAnimal savedAnimal = EndangeredAnimal.find(testAnimal.getId());
    assertEquals("Good", savedAnimal.getHealth());
  }

//update the health of the endangered animal
  @Test
  public void update_preservesOriginalHealth_Good() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    testAnimal.save();
    testAnimal.update();
    EndangeredAnimal savedAnimal = EndangeredAnimal.find(testAnimal.getId());
    assertEquals("Good", savedAnimal.getHealth());
  }

//updates the new health of the endangered animal
  @Test
  public void update_savesNewHealthToDB_Poor() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    testAnimal.save();
    testAnimal.setHealth("Poor");
    testAnimal.update();
    EndangeredAnimal savedAnimal = EndangeredAnimal.find(testAnimal.getId());
    assertEquals("Poor", savedAnimal.getHealth());
  }

  // save the id of the endangered animal
  @Test
  public void save_setsTheId_int() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    testAnimal.save();
    assertTrue(testAnimal.getId() > 0);
  }

//check if the endangered animal put into DB is true
  @Test
  public void save_insertsObjectIntoDB_true() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    testAnimal.save();
    EndangeredAnimal savedAnimal = EndangeredAnimal.find(testAnimal.getId());
    assertTrue(testAnimal.equals(savedAnimal));
  }

//throw an exception if the name the endangered animal exists especially when the ranger tries to resave the animal again
  @Test(expected = IllegalArgumentException.class)
  public void save_cannotSaveIfNameAlreadyExists_IllegalArgumentException() {
    EndangeredAnimal firstAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    firstAnimal.save();
    EndangeredAnimal secondAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    secondAnimal.save();
  }

//update the animal id
  @Test
  public void update_preservesOriginalId_true() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    testAnimal.save();
    testAnimal.update();
    EndangeredAnimal savedAnimal = EndangeredAnimal.find(testAnimal.getId());
    assertEquals(testAnimal.getId(), savedAnimal.getId());
  }

// to prevent saving a name if its already exists
  @Test(expected = IllegalArgumentException.class)
  public void update_cannotSaveIfNameAlreadyExists_IllegalArgumentException() {
    EndangeredAnimal firstAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    firstAnimal.save();
    EndangeredAnimal secondAnimal = new EndangeredAnimal("Panda", 1.5, "Good");
    secondAnimal.save();
    secondAnimal.setName("Rhino");
    secondAnimal.update();
  }

//delete endangered animals if they are no longer endangered
  @Test
  public void delete_removesObjectFromDB_null() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    testAnimal.save();
    testAnimal.delete();
    EndangeredAnimal savedAnimal = EndangeredAnimal.find(testAnimal.getId());
    assertEquals(null, savedAnimal);
  }

//return all the data of endangered animals in the database
  @Test
  public void all_getsAllObjectsFromDatabase_true() {
    EndangeredAnimal firstAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    firstAnimal.save();
    EndangeredAnimal secondAnimal = new EndangeredAnimal("Panda", 1.5, "Good");
    secondAnimal.save();
    EndangeredAnimal[] expected = { firstAnimal, secondAnimal };
    assertTrue(EndangeredAnimal.all().containsAll(Arrays.asList(expected)));
  }

//return nothing if the animal searched does not exist in the database
  @Test
  public void search_returnsNothingForUnknownValue_emptyList() {
    EndangeredAnimal firstAnimal = new EndangeredAnimal("Panda", 1.5, "Good");
    firstAnimal.save();
    EndangeredAnimal secondAnimal = new EndangeredAnimal("Rhino", 1.5, "Good");
    secondAnimal.save();
    List<EndangeredAnimal> foundAnimals = EndangeredAnimal.search("fox");
    assertEquals(Collections.<EndangeredAnimal>emptyList(), foundAnimals);
  }

//return all matching objects true
  @Test
  public void search_returnsAllMatchingObjects_true() {
    EndangeredAnimal firstAnimal = new EndangeredAnimal("Asian Elephant", 1.5, "Good");
    firstAnimal.save();
    EndangeredAnimal secondAnimal = new EndangeredAnimal("Indian Elephant", 1.5, "Good");
    secondAnimal.save();
    EndangeredAnimal thirdEndangeredAnimal = new EndangeredAnimal("Panda", 1.5, "Good");
    thirdEndangeredAnimal.save();
    List<EndangeredAnimal> foundAnimals = EndangeredAnimal.search("elep");
    EndangeredAnimal[] expected = { firstAnimal, secondAnimal };
    assertEquals(Arrays.asList(expected), foundAnimals);
  }

  //returns true if the properties of the endangered animal are correct
  @Test
  public void equals_objectIsEqualIfAllPropertiesAreEqual_true() {
    EndangeredAnimal firstAnimal = new EndangeredAnimal("Rabbit", 1.5, "Good");
    EndangeredAnimal secondAnimal = new EndangeredAnimal("Rabbit", 1.5, "Good");
    assertTrue(firstAnimal.equals(secondAnimal));
  }

//return false if the id of an endangered animal does not exist
  @Test
  public void idExists_isFalseWhenIdDoesNotExists_false() {
    assertFalse(Animal.idExists(1));
  }

//return true if id exists
  @Test
  public void idExists_isTrueWhenIdDoesNotExists_true() {
    EndangeredAnimal firstAnimal = new EndangeredAnimal("Rabbit", 1.5, "Good");
    firstAnimal.save();
    assertTrue(Animal.idExists(firstAnimal.getId()));
  }

//sightings should be associated with the id
  @Test
  public void getSightings_getsSightingAssociatedWithId_Sighting() {
    EndangeredAnimal testAnimal = new EndangeredAnimal("Rabbit", 1.5, "Good");
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
