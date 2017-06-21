//imports
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.sql.Timestamp;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

//class RangerTest
public class RangerTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  // ranger credentials are instantiated correctly
  @Test
  public void ranger_instantiatesCorrectly_true() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    assertTrue(testRanger instanceof Ranger);
  }

//a new ranger can be intantiated without id
  @Test
  public void ranger_instantiatesWithoutId_0() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    assertEquals(0, testRanger.getId());
  }

  // ranger username should be instantiated correctly
  @Test
  public void ranger_instantiatesWithName_User() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    assertEquals("User", testRanger.getUserName());
  }

//throws an exception where a ranger cannot instantiate a empty userName
  @Test(expected = IllegalArgumentException.class)
  public void ranger_cannotInstantiateEmptyUserName_IllegalArgumentException() {
    Ranger testRanger = new Ranger("", "Bob", "Smith", 1, 5035550000L);
  }

//sets the userName of the ranger
  @Test
  public void setUserName_setsANewName_NewUser() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.setUserName("NewUser");
    assertEquals("NewUser", testRanger.getUserName());
  }

//throws an exception where a username of a new ranger cannot be empty
  @Test(expected = IllegalArgumentException.class)
  public void setUserName_cannotSetEmptyName_IllegalArgumentException() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.setUserName("");
  }

//saves the rangers usernames to the database
  @Test
  public void save_savesUserNameToDB_User() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    Ranger savedRanger = Ranger.find(testRanger.getId());
    assertEquals("User", savedRanger.getUserName());
  }

//updates rangers username to the database
  @Test
  public void update_preservesOriginalUserName_User() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    testRanger.update();
    Ranger savedRanger = Ranger.find(testRanger.getId());
    assertEquals("User", savedRanger.getUserName());
  }

//updates new rangers username to the database
  @Test
  public void update_savesNewUserNameToDB_NewUser() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    testRanger.setUserName("NewUser");
    testRanger.update();
    Ranger savedRanger = Ranger.find(testRanger.getId());
    assertEquals("NewUser", savedRanger.getUserName());
  }


  // ranger first ane is instantiated correctly
  @Test
  public void ranger_instantiatesWithName_Bob() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    assertEquals("Bob", testRanger.getFirstName());
  }

//ranger cannot instantiate an empty first name
  @Test(expected = IllegalArgumentException.class)
  public void ranger_cannotInstantiateEmptyFirstName_IllegalArgumentException() {
    Ranger testRanger = new Ranger("User", "", "Smith", 1, 5035550000L);
  }

//ranger can set the first name a new name
  @Test
  public void setFirstName_setsANewName_Tom() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.setFirstName("Tom");
    assertEquals("Tom", testRanger.getFirstName());
  }

//throws an exception when the set name is empty
  @Test(expected = IllegalArgumentException.class)
  public void setName_cannotSetEmptyName_IllegalArgumentException() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.setFirstName("");
  }

//saves the first name of the ranger to the database
  @Test
  public void save_savesFirstNameToDB_Bob() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    Ranger savedRanger = Ranger.find(testRanger.getId());
    assertEquals("Bob", savedRanger.getFirstName());
  }

//updates the first name of the ranger
  @Test
  public void update_preservesOriginalFirstName_Bob() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    testRanger.update();
    Ranger savedRanger = Ranger.find(testRanger.getId());
    assertEquals("Bob", savedRanger.getFirstName());
  }

//updates the new ranger first name
  @Test
  public void update_savesNewFirstNameToDB_Tom() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    testRanger.setFirstName("Tom");
    testRanger.update();
    Ranger savedRanger = Ranger.find(testRanger.getId());
    assertEquals("Tom", savedRanger.getFirstName());
  }

  // ranger instantiates with last name is correct
  @Test
  public void ranger_instantiatesWithName_Smith() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    assertEquals("Smith", testRanger.getLastName());
  }

//throws an exception where a ranger cannot instantiate an empty last name
  @Test(expected = IllegalArgumentException.class)
  public void ranger_cannotInstantiateEmptyLastName_IllegalArgumentException() {
    Ranger testRanger = new Ranger("User", "Bob", "", 1, 5035550000L);
  }

//sets the new last name of the new ranger
  @Test
  public void setFirstName_setsANewName_Johnson() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.setLastName("Johnson");
    assertEquals("Johnson", testRanger.getLastName());
  }

// throws an exception to prevent saving an empty last name
  @Test(expected = IllegalArgumentException.class)
  public void setLastName_cannotSetEmptyName_IllegalArgumentException() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.setLastName("");
  }

//saves the last name of the ranger
  @Test
  public void save_savesLastNameToDB_Smith() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    Ranger savedRanger = Ranger.find(testRanger.getId());
    assertEquals("Smith", savedRanger.getLastName());
  }

//update the last name of the ranger
  @Test
  public void update_preservesOriginalLastName_Smith() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    testRanger.update();
    Ranger savedRanger = Ranger.find(testRanger.getId());
    assertEquals("Smith", savedRanger.getLastName());
  }

//updates the last name of the new ranger
  @Test
  public void update_savesNewLastNameToDB_Johnson() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    testRanger.setLastName("Johnson");
    testRanger.update();
    Ranger savedRanger = Ranger.find(testRanger.getId());
    assertEquals("Johnson", savedRanger.getLastName());
  }

  // badge of the ranger is instantiated correctly
  @Test
  public void ranger_instantiatesWithBadge_1() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    assertEquals(1, testRanger.getBadge());
  }

//sets a new badge of the ranger
  @Test
  public void setBadge_setsANewBadge_2() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.setBadge(2);
    assertEquals(2, testRanger.getBadge());
  }

//saves rangers badge
  @Test
  public void save_savesBadgeToDB_1() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    Ranger savedRanger = Ranger.find(testRanger.getId());
    assertEquals(1, savedRanger.getBadge());
  }

// updates the badge of the ranger
  @Test
  public void update_preservesOriginalBadge_1() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    testRanger.update();
    Ranger savedRanger = Ranger.find(testRanger.getId());
    assertEquals(1, savedRanger.getBadge());
  }

//updates the badge of the new ranger ie if promoted
  @Test
  public void update_savesNewBadgeToDB_2() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    testRanger.setBadge(2);
    testRanger.update();
    Ranger savedRanger = Ranger.find(testRanger.getId());
    assertEquals(2, savedRanger.getBadge());
  }

  // ranger's contacts is instantiated correctly
  @Test
  public void ranger_instantiatesWithPhone_1() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    assertEquals(5035550000L, testRanger.getPhone());
  }

//set a new badge and phone to the ranger
  @Test
  public void setBadge_setsANewPhone_3601234567() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.setPhone(3601234567L);
    assertEquals(3601234567L, testRanger.getPhone());
  }

//sav contacts of the ranger
  @Test
  public void save_savesPhoneToDB_5035550000() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    Ranger savedRanger = Ranger.find(testRanger.getId());
    assertEquals(5035550000L, savedRanger.getPhone());
  }

//updates the rangers phone numbers
  @Test
  public void update_preservesOriginalPhone_5035550000() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    testRanger.update();
    Ranger savedRanger = Ranger.find(testRanger.getId());
    assertEquals(5035550000L, savedRanger.getPhone());
  }

//update the new rangers phone numbers
  @Test
  public void update_savesNewPhoneToDB_3601234567() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    testRanger.setPhone(3601234567L);
    testRanger.update();
    Ranger savedRanger = Ranger.find(testRanger.getId());
    assertEquals(3601234567L, savedRanger.getPhone());
  }

  // save the ranger id to the database
  @Test
  public void save_setsTheId_int() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    assertTrue(testRanger.getId() > 0);
  }

//save the ranger to the database
  @Test
  public void save_insertsObjectIntoDB_true() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    Ranger savedRanger = Ranger.find(testRanger.getId());
    assertTrue(testRanger.equals(savedRanger));
  }

//throws an exception when one tries to save a name which already exists
  @Test(expected = IllegalArgumentException.class)
  public void save_cannotSaveIfNameAlreadyExists_IllegalArgumentException() {
    Ranger firstRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    firstRanger.save();
    Ranger secondRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    secondRanger.save();
  }

//updates the rangerId
  @Test
  public void update_preservesOriginalId_true() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    testRanger.update();
    Ranger savedRanger = Ranger.find(testRanger.getId());
    assertEquals(testRanger.getId(), savedRanger.getId());
  }

//throws an exception if one tries to update rangers names to look like the existing save names
  @Test(expected = IllegalArgumentException.class)
  public void update_cannotSaveIfNameAlreadyExists_IllegalArgumentException() {
    Ranger firstRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    firstRanger.save();
    Ranger secondRanger = new Ranger("NewUser", "Bob", "Smith", 1, 5035550000L);
    secondRanger.save();
    secondRanger.setUserName("User");
    secondRanger.update();
  }

//delete rangers credentials
  @Test
  public void delete_removesObjectFromDB_null() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
    testRanger.delete();
    Ranger savedRanger = Ranger.find(testRanger.getId());
    assertEquals(null, savedRanger);
  }

//return all rangers credentials from the database if true
  @Test
  public void all_getsAllObjectsFromDatabase_true() {
    Ranger firstRanger = new Ranger("User", "Tom", "Smith", 1, 5035550000L);
    firstRanger.save();
    Ranger secondRanger = new Ranger("NewUser", "Tommy", "Smith", 1, 5035550000L);
    secondRanger.save();
    Ranger[] expected = { firstRanger, secondRanger };
    assertTrue(Ranger.all().containsAll(Arrays.asList(expected)));
  }

//return nothing if rangers credentials don't exist
  @Test
  public void search_returnsNothingForUnknownValue_emptyList() {
    Ranger firstRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    firstRanger.save();
    Ranger secondRanger = new Ranger("NewUser", "Bob", "Smith", 1, 5035550000L);
    secondRanger.save();
    List<Ranger> foundRangers = Ranger.search("tom");
    assertEquals(Collections.<Ranger>emptyList(), foundRangers);
  }

//return the searched ranger credentials if true
  @Test
  public void search_returnsAllMatchingObjects_true() {
    Ranger firstRanger = new Ranger("User", "Tom", "Smith", 1, 5035550000L);
    firstRanger.save();
    Ranger secondRanger = new Ranger("NewUser", "Tommy", "Smith", 1, 5035550000L);
    secondRanger.save();
    Ranger thirdRanger = new Ranger("Tom", "John", "Smith", 1, 5035550000L);
    thirdRanger.save();
    Ranger fourthRanger = new Ranger("AnotherNewUser", "George", "Tompson", 1, 5035550000L);
    fourthRanger.save();
    Ranger fifthRanger = new Ranger("YetAnotherNewUser", "Mike", "Smith", 1, 5035550000L);
    fifthRanger.save();
    Ranger[] expected = { firstRanger, secondRanger, thirdRanger, fourthRanger };
    List<Ranger> foundRangers = Ranger.search("tom");
    assertEquals(Arrays.asList(expected), foundRangers);
    assertFalse(foundRangers.contains(fifthRanger));
  }


  //ranger credentials are correct it should return true
  @Test
  public void equals_objectIsEqualIfAllPropertiesAreEqual_true() {
    Ranger firstRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    Ranger secondRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    assertTrue(firstRanger.equals(secondRanger));
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
    List<Sighting> foundSighting = testRanger.getSightings();
    Sighting[] expected = { testSighting };
    assertTrue(foundSighting.containsAll(Arrays.asList(expected)));
  }


}
