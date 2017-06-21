// interface DatabaseManagement
public interface DatabaseManagement {
  public void save();
  public void update();
  public void delete();
  public boolean equals(Object otherObject);
  public static final int MIN_NAME_LENGTH = 1;
  public static final int MIN_AGE = 0;

//for name validation where ranger cannot enter an empty name
  public static boolean nameValidation(String name) {
    if(name.length() < MIN_NAME_LENGTH) {
      throw new IllegalArgumentException("Error: Name cannot be empty");
    } else {
      return true;
    }
  }

// check the age validation where it cannot be a negative
  public static boolean ageValidation(double age) {
    if(age < MIN_AGE) {
      throw new IllegalArgumentException("Error: Age cannot be negative");
    } else {
      return true;
    }
  }
}
